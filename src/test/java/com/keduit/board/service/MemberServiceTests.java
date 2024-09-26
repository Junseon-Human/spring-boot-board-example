package com.keduit.board.service;

import com.keduit.board.constant.Role;
import com.keduit.board.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class MemberServiceTests {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {
        Member member = new Member();
        String password = passwordEncoder.encode("1234");
        member.setPassword(password);
        member.setId("aa");
        member.setName("김준선");
        member.setRole(Role.ADMIN);
        Member savedMember = memberService.saveMember(member);
        System.out.println(savedMember);

        assertEquals(member.getId(), savedMember.getId());
    }

}
