package com.keduit.board.entity;

import com.keduit.board.dto.ReplyDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Helper;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Reply extends  BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    private String reply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 댓글 작성자와 연결

    public static Reply createReply(ReplyDTO replyDTO, Board board, Member member) {
        Reply reply = new Reply();
        reply.setReply(replyDTO.getReply());
        reply.setMember(member);
        reply.setBoard(board);

        return reply;
    }
}
