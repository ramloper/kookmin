package org.kookmin.demo.MemberRepositoryTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kookmin.demo.common.MemberRole;
import org.kookmin.demo.domain.Member;
import org.kookmin.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder encoder;

    @BeforeEach
    public void before(){
        IntStream.rangeClosed(1,10).forEach(i -> {
            Member member = Member.builder()
                    .username("member"+i)
                    .password(encoder.encode("1234"))
                    .phoneNumber("000-0000-0000")
                    .build();
            member.addRole(MemberRole.USER);

            if (i >= 9) member.addRole(MemberRole.ADMIN);
            memberRepository.save(member);
        });
    }
    @Test
    public void insertMember(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = Member.builder()
                    .username("member"+i)
                    .password(encoder.encode("1234"))
                    .phoneNumber("000-0000-0000")
                    .build();
            member.addRole(MemberRole.USER);

            if (i >= 90) member.addRole(MemberRole.ADMIN);
            memberRepository.save(member);
        });
    }

    @Test
    public void testRead(){

        Member member = memberRepository.getWithRoles("member10").orElseThrow();

        System.out.println(member);
        System.out.println(member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> System.out.println(memberRole.name()));
    }
}
