package com.keduit.board.service;

import com.keduit.board.dto.BoardDTO;
import com.keduit.board.dto.BoardSearchDTO;
import com.keduit.board.entity.Board;
import com.keduit.board.entity.Member;
import com.keduit.board.repository.BoardRepository;
import com.keduit.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Page<Board> getBoardsPage(BoardSearchDTO boardSearchDTO, Pageable pageable) {
        return boardRepository.getBoardsPage(boardSearchDTO, pageable);
    }


    public void saveBoard(BoardDTO boardDTO, String name) {
        Member member = memberRepository.findByName(name);
        Board board = boardDTO.createBoard(member);
        boardRepository.save(board);
    }

    public Board getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    public BoardDTO getBoardDtl(Long BoardId) {
        Board board = boardRepository.findById(BoardId).orElseThrow(EntityNotFoundException::new);

        return BoardDTO.of(board);
    }
}
