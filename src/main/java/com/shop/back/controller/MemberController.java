package com.shop.back.controller;

import com.shop.back.controller.dto.request.MemberRequest;
import com.shop.back.controller.dto.response.MemberResponse;
import com.shop.back.domain.Address;
import com.shop.back.domain.Member;
import com.shop.back.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

//    @GetMapping("/new")
//    public String createForm(Model model) {
//        model.addAttribute("memberForm", new MemberForm());
//        return "members/createMemberForm";
//    }

    @PostMapping("/new")
    public ResponseEntity<MemberResponse> create(@RequestBody @Valid MemberRequest memberRequest) {

//        if (result.hasErrors()) {
//            return "members/createMemberForm";
//        }
        return ResponseEntity.ok(memberService.join(memberRequest));

    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }
}
