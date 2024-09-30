package com.keduit.board.repository;

import com.keduit.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    Board findByBoardId(Long boardId);
}
