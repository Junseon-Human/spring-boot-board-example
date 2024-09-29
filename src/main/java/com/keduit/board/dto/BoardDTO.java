package com.keduit.board.dto;

import com.keduit.board.entity.Board;
import com.keduit.board.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BoardDTO {

    Long boardId;

    @NotEmpty(message = "제목은 필수 입력 입니다.")
    private String title;

    @NotEmpty(message = "제목은 필수 입력 입니다.")
    private String content;

    private static ModelMapper modelMapper = new ModelMapper();

    // DTO -> Entity
    public Board createBoard(Member member) {
        ModelMapper modelMapper = new ModelMapper();
        Board board = modelMapper.map(this, Board.class);
        board.setMember(member);
        return board;
    }

    public static BoardDTO of(Board board) {
        return modelMapper.map(board, BoardDTO.class);
    }




}
