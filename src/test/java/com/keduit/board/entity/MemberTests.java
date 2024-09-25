package com.keduit.board.entity;

import com.keduit.board.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class MemberTests {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;



    @Test
    @DisplayName("Auditing Test")
    @WithMockUser(username = "Kim", roles = "USER")
    public void auditingTest() {
        Member newMember = new Member();
        memberRepository.save(newMember);

        em.flush();
        em.clear();
        Member member = memberRepository.findById(newMember.getMemberId()).orElseThrow(EntityNotFoundException::new);

        System.out.println(member.getRegTime());
    }
}
