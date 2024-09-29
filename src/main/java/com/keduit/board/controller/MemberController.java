package com.keduit.board.controller;

import com.keduit.board.dto.MemberDTO;
import com.keduit.board.entity.Member;
import com.keduit.board.repository.MemberRepository;
import com.keduit.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String memberForm(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberDTO, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";

    }

    @GetMapping("/login")
    public String loginMember() {
        return "member/memberLoginForm";
    }

    @GetMapping("/login/error")
    public  String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디또는 비밀번호를 확인해주세요");
        return "member/memberLoginForm";
    }

}
