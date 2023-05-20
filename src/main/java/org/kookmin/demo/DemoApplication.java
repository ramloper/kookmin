package org.kookmin.demo;

import org.kookmin.demo.common.EducationStatus;
import org.kookmin.demo.common.MemberRole;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Member;
import org.kookmin.demo.repository.EducationRepository;
import org.kookmin.demo.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.stream.IntStream;

@SpringBootApplication
public class DemoApplication {

	@Profile("dev")
	@Bean
	CommandLineRunner initData(MemberRepository memberRepository,
							   EducationRepository educationRepository,
							   PasswordEncoder passwordEncoder) {

		return (args)->{
			memberRepository.save(Member.builder()
					.username("admin")
					.password(passwordEncoder.encode("1234"))
					.name("관리자")
					.phoneNumber("010-1234-1234")
					.roleSet(Collections.singleton(MemberRole.ADMIN))
					.build());
			IntStream.rangeClosed(1, 9).forEach(value -> {
				memberRepository.save(Member.builder()
						.username("J202233"+value)
						.password(passwordEncoder.encode("1234"))
						.name("유저"+value)
						.phoneNumber("010-1234-1234")
						.roleSet(Collections.singleton(MemberRole.USER))
						.build());
			});

			IntStream.rangeClosed(1, 10).forEach(value -> {
				educationRepository.save(Education.builder()
						.id(value)
						.name("미술교육책")
						.fileName("test.jpg")
						.status(EducationStatus.WAITING)
						.build());
			});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
