package com.keduit.board.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    private String reply;
    private String writer; // 댓글 작성자의 id
    private LocalDateTime regdate;
    private LocalDateTime updatedate;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 댓글 작성자와 연결
}
