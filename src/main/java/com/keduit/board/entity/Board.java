package com.keduit.board.entity;

import com.keduit.board.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Reply> replies;

    public void updateBoard(BoardDTO boardDTO) {
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
        this.setUpdateTime(LocalDateTime.now());
    }
}
