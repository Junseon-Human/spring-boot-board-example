package com.keduit.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class MemberDTO {

    @NotBlank(message = "이름은 필수 입력입니다.")
    private String name;

    @NotEmpty(message = "아이디는 필수 입력입니다.")
    private  String id;

    @NotEmpty(message = "비밀번호는 필수 입력입니다.")
    @Length(min = 4, max=16, message = "비밀번호는 4자이상 16자 이하로 입력해주세요")
    private String password;

}
