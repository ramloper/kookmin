package org.kookmin.demo.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kookmin.demo.domain.Member;
import org.kookmin.demo.repository.MemberRepository;
import org.kookmin.demo.security.dto.MemberSecurityDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.getWithRoles(username).orElseThrow(
                () -> new UsernameNotFoundException("Username Not Found"));

        MemberSecurityDTO dto = new MemberSecurityDTO(
                member.getUsername(),
                member.getPassword(),
                member.getName(),
                member.getPhoneNumber(),
                member.getRoleSet().stream().map(
                        memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name()))
                        .collect(Collectors.toList())
        );

        return dto;
    }
}