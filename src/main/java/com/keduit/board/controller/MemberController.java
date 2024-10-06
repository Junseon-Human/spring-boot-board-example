package com.keduit.board.controller;

import com.keduit.board.dto.MemberDTO;
import com.keduit.board.dto.NaverApi;
import com.keduit.board.dto.NaverProfile;
import com.keduit.board.entity.Member;
import com.keduit.board.repository.MemberRepository;
import com.keduit.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final NaverApi naverApi;
    @Value("${kakao.client_id}")
    private String client_id;

    @Value("${kakao.redirect_uri}")
    private String redirect_uri;

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
    public String loginMember(Model model) {
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+client_id+"&redirect_uri="+redirect_uri;
        model.addAttribute("naverClientId", naverApi.getNaverClientId());
        model.addAttribute("naverRedirectUri",naverApi.getNaverRedirectUri());
        model.addAttribute("state", "STATE_STRING");
        model.addAttribute("location", location);
        return "member/memberLoginForm";
    }

    @GetMapping("/login/error")
    public  String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디또는 비밀번호를 확인해주세요");
        return "member/memberLoginForm";
    }

    @ResponseBody
    @GetMapping("/login/naver/code")
    public Map<String, Object> naverLogin(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state)
    {
        Map<String, Object> map = new HashMap<>();
        // 1. 인가 코드 받기 -> @RequestParam String code

        // 2. 접근 토큰 발급 요청
        String accessToken = naverApi.getAccessToken(code, state);
        System.out.println("accessToken = " + accessToken);

        // 3. 사용자 정보 받기
        NaverProfile userInfo = naverApi.getUserInfo(accessToken);
        map.put("id", userInfo.getId());
        map.put("nickName", userInfo.getNickname());
        map.put("email", userInfo.getEmail());
        map.put("mobile", userInfo.getMobile());

        return map;
    }

}
