package com.keduit.board.repository;

import com.keduit.board.dto.BoardDTO;
import com.keduit.board.dto.MemberDTO;
import com.keduit.board.dto.ReplyDTO;
import com.keduit.board.entity.Board;
import com.keduit.board.entity.Member;
import com.keduit.board.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@SpringBootTest
//@Transactional
public class RepositoryTests {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ReplyRepository replyRepository;

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

    @Test
    @DisplayName("글작성 테스트")
    public void writeFromBoard() {
        Member member = memberRepository.findById(5L).orElseThrow(EntityNotFoundException::new);

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setContent("테스트 글내용");
        boardDTO.setTitle("테스트 글 제목");

        Board board = boardDTO.createBoard(member);
        System.out.println(board.getContent());
        boardRepository.save(board);

    }

    @Test
    @DisplayName("댓글 작성 테스트")
    public void writeReply() {
        Member member = memberRepository.findById(5L).orElseThrow(EntityExistsException::new);
        Board board = boardRepository.findById(6L).orElseThrow(EntityNotFoundException::new);

        ReplyDTO replyDTO = new ReplyDTO();

        replyDTO.setReply("테스트 댓글");

        Reply reply = Reply.createReply(replyDTO, board, member);
        replyRepository.save(reply);
    }





}
