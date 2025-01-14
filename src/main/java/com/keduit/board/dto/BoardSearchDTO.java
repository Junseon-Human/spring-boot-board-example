package com.keduit.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardSearchDTO {

    private String searchDateType;

    private String searchBy;

    private String searchQuery = "";

}
