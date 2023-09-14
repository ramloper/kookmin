package org.kookmin.demo;

import org.kookmin.demo.common.EducationStatus;
import org.kookmin.demo.common.MemberRole;
import org.kookmin.demo.common.MemberStatus;
import org.kookmin.demo.common.RentalStatus;
import org.kookmin.demo.domain.*;
import org.kookmin.demo.repository.*;
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
							   DayOfWeekRepository dayOfWeekRepository,
							   EducationRepository educationRepository,
							   RentalRepository rentalRepository,
							   NotificationRepository notificationRepository,
							   PasswordEncoder passwordEncoder) {

		return (args)->{
			dayOfWeekRepository.save(DayOfWeek.builder()
					.id(1)
					.monDay(false)
					.tuesDay(true)
					.wednesDay(false)
					.thursDay(true)
					.friDay(false)
					.build());
			dayOfWeekRepository.save(DayOfWeek.builder()
					.id(2)
					.monDay(false)
					.tuesDay(true)
					.wednesDay(false)
					.thursDay(true)
					.friDay(false)
					.build());
			memberRepository.save(Member.builder()
					.username("admin")
					.password(passwordEncoder.encode("1234"))
					.MemberName("관리자")
					.phoneNumber("01094333753")
					.roleSet(Collections.singleton(MemberRole.ADMIN))
					.status(MemberStatus.ACTIVE)
					.build());
			Member member = memberRepository.save(Member.builder()
					.username("user")
					.password(passwordEncoder.encode("1234"))
					.MemberName("유저")
					.phoneNumber("01089361166")
					.roleSet(Collections.singleton(MemberRole.USER))
					.status(MemberStatus.ACTIVE)
					.build());

			IntStream.rangeClosed(1, 9).forEach(value -> {
				memberRepository.save(Member.builder()
						.username("J202233"+value)
						.password(passwordEncoder.encode("1234"))
						.MemberName("유저이름"+value)
						.phoneNumber("01012341234")
						.roleSet(Collections.singleton(MemberRole.USER))
						.status(MemberStatus.ACTIVE)
						.build());
			});
			IntStream.rangeClosed(1, 10).forEach(value -> {
				educationRepository.save(Education.builder()
						.id(value)
						.bookName("미술교육책."+value)
						.originFileName("test11.jpg")
						.uploadFileName("test11.jpg")
						.publisher("미진사")
						.writer("박지혜")
						.translator("김우람")
						.status(EducationStatus.UNAVAILABLE)
						.build());
			});
			IntStream.rangeClosed(11, 30).forEach(value -> {
				educationRepository.save(Education.builder()
						.id(value)
						.bookName("12test"+value)
						.originFileName("12test.jpg")
						.uploadFileName("12test.jpg")
						.publisher("미진사")
						.writer("박지혜")
						.translator("김우람")
						.status(EducationStatus.AVAILABLE)
						.build());
			});


			IntStream.rangeClosed(1, 5).forEach(value -> {
				rentalRepository.save(Rental.builder()
						.id(value)
						.member(member)
						.education(educationRepository.findById(value).orElseThrow())
						.rentalDate("2023-08-10")
						.returnDate("2023-08-21")
						.status(RentalStatus.WAITING)
						.build());

			});
			IntStream.rangeClosed(6, 10).forEach(value -> {
				rentalRepository.save(Rental.builder()
						.id(value)
						.member(member)
						.education(educationRepository.findById(value).orElseThrow())
						.rentalDate("2023-08-10")
						.returnDate("2023-08-21")
						.status(RentalStatus.RESERVED)
						.build());

			});
			notificationRepository.save(Notification.builder()
					.title("testTitle")
					.content("testContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContent")
					.aBoolean(true)
					.build());
			notificationRepository.save(Notification.builder()
					.title("testTitle")
					.content("testContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContent")
					.aBoolean(false)
					.build());
		};
	}

	@Profile("prod")
	@Bean
	CommandLineRunner initDataAdmin(MemberRepository memberRepository,
									PasswordEncoder passwordEncoder,
									DayOfWeekRepository dayOfWeekRepository) {


		return (args)->{
			memberRepository.save(Member.builder()
					.username("admin")
					.password(passwordEncoder.encode("ALTNFRYDBR!!213"))
					.MemberName("관리자")
					.phoneNumber("010-8936-1166")
					.roleSet(Collections.singleton(MemberRole.ADMIN))
					.status(MemberStatus.ACTIVE)
					.build());

			dayOfWeekRepository.save(DayOfWeek.builder()
					.id(1)
					.monDay(false)
					.tuesDay(true)
					.wednesDay(false)
					.thursDay(true)
					.friDay(false)
					.build());
			dayOfWeekRepository.save(DayOfWeek.builder()
					.id(2)
					.monDay(false)
					.tuesDay(true)
					.wednesDay(false)
					.thursDay(true)
					.friDay(false)
					.build());
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
