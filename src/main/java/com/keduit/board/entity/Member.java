package com.keduit.board.entity;


import com.keduit.board.constant.Role;
import com.keduit.board.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;

    private String password;

    @Column(unique = true)
    private String id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Board> boards;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Reply> replies;

    public static Member createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setName(memberDTO.getName());
        String password = passwordEncoder.encode(memberDTO.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }
}
