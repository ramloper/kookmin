package org.kookmin.demo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.kookmin.demo.security.CustomUserDetailsService;
import org.kookmin.demo.security.handler.Custom403;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log4j2
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/main/**").authenticated()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/list").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/main")
                .failureUrl("/user/login?err=e")
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .key("12345678")
                .tokenValiditySeconds(60*60*24*30)
                .and()
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        log.info("===============web configure=============");

        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        //requestMatchers() 메서드에 PathRequest.toStaticResources().atCommonLocations()를 전달하여 정적 리소스 요청을 무시하도록 설정합니다. 이는 CSS, JavaScript, 이미지 등의 정적 리소스에 대한 요청이 Spring Security의 보안 검사를 거치지 않도록 합니다.
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){return new Custom403();
    }

}
