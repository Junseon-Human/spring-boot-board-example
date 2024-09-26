package com.keduit.board.entity;

import com.keduit.board.repository.BoardRepository;
import com.keduit.board.repository.MemberRepository;
import com.keduit.board.repository.ReplyRepository;
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
public class EntityTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ReplyRepository replyRepository;

    @PersistenceContext
    EntityManager em;



    @Test
    @DisplayName("Member Auditing Test")
    @WithMockUser(username = "Kim", roles = "USER")
    public void auditingTest() {
        Member newMember = new Member();
        newMember.setId("kimkvaa");
        newMember.setPassword("1234");
        memberRepository.save(newMember);

        em.flush();
        em.clear();
        Member member = memberRepository.findById(newMember.getMemberId()).orElseThrow(EntityNotFoundException::new);
        System.out.println("member===================" + member.getId());
        System.out.println(member.getPassword());

        System.out.println(member.getRegTime());
    }

    @Test
    @DisplayName("Board Test")
    @WithMockUser(username = "Kim", roles = "USER")
    public void BoardTest() {
        Board newBoard = new Board();
        boardRepository.save(newBoard);


        em.flush();
        em.clear();
        Board board = boardRepository.findById(newBoard.getBoardId()).orElseThrow(EntityNotFoundException::new);
        System.out.println(board.getBoardId());

        System.out.println(board.getRegTime());
        System.out.println(board.getCreateBy());

    }

}
