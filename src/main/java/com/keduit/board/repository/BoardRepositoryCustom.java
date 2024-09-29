package com.keduit.board.repository;

import com.keduit.board.dto.BoardSearchDTO;
import com.keduit.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<Board> getBoardsPage(BoardSearchDTO boardSearchDTO, Pageable pageable);
}
