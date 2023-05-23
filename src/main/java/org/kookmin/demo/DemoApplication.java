package org.kookmin.demo;

import org.kookmin.demo.common.EducationStatus;
import org.kookmin.demo.common.MemberRole;
import org.kookmin.demo.common.MemberStatus;
import org.kookmin.demo.common.RentalStatus;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Member;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.repository.EducationRepository;
import org.kookmin.demo.repository.MemberRepository;
import org.kookmin.demo.repository.RentalRepository;
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
							   RentalRepository rentalRepository,
							   PasswordEncoder passwordEncoder) {

		return (args)->{
			memberRepository.save(Member.builder()
					.username("admin")
					.password(passwordEncoder.encode("1234"))
					.MemberName("관리자")
					.phoneNumber("010-1234-1234")
					.roleSet(Collections.singleton(MemberRole.ADMIN))
					.status(MemberStatus.ACTIVE)
					.build());
			Member member = memberRepository.save(Member.builder()
					.username("user")
					.password(passwordEncoder.encode("1234"))
					.MemberName("유저")
					.phoneNumber("010-1234-1234")
					.roleSet(Collections.singleton(MemberRole.USER))
					.status(MemberStatus.ACTIVE)
					.build());

			IntStream.rangeClosed(1, 9).forEach(value -> {
				memberRepository.save(Member.builder()
						.username("J202233"+value)
						.password(passwordEncoder.encode("1234"))
						.MemberName("유저이름"+value)
						.phoneNumber("010-1234-1234")
						.roleSet(Collections.singleton(MemberRole.USER))
						.status(MemberStatus.ACTIVE)
						.build());
			});

			IntStream.rangeClosed(1, 30).forEach(value -> {
				educationRepository.save(Education.builder()
						.id(value)
						.bookName("미술교육책."+value)
						.fileName("test.jpg")
						.publisher("미진사")
						.writer("박지혜")
						.translator("김우람")
						.status(EducationStatus.AVAILABLE)
						.build());
			});


			IntStream.rangeClosed(1, 10).forEach(value -> {
				rentalRepository.save(Rental.builder()
						.id(value)
						.member(member)
						.education(educationRepository.findById(value).orElseThrow())
						.status(RentalStatus.WAITING)
						.build());

			});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
