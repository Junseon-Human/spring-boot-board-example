package com.keduit.board.dto;

import com.keduit.board.entity.Board;
import com.keduit.board.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
public class BoardDTO {

    private String title;

    private String content;

    private static ModelMapper modelMapper = new ModelMapper();

    // DTO -> Entity
    public Board createBoard(Member member) {
        ModelMapper modelMapper = new ModelMapper();
        Board board = modelMapper.map(this, Board.class);
        board.setMember(member);
        return board;
    }

}
