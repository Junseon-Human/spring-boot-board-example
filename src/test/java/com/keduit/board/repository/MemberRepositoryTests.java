package com.keduit.board.repository;

import com.keduit.board.dto.MemberDTO;
import com.keduit.board.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 테스트")
    public void createMemberTest() {
        MemberDTO member = new MemberDTO();
        member.setId("kimkva");
        member.setPassword("1234");
        member.setName("김준선");

        Member mem = Member.createMember(member, passwordEncoder);
        memberRepository.save(mem);
    }

}
