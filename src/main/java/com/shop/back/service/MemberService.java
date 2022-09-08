package com.shop.back.service;

import com.shop.back.controller.dto.request.MemberRequest;
import com.shop.back.controller.dto.response.MemberResponse;
import com.shop.back.domain.Member;
import com.shop.back.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public MemberResponse join(MemberRequest memberRequest) {
        validateDuplicateMember(memberRequest); //중복 회원 검증
//        memberRepository.save(member);
        return MemberResponse.from(memberRepository.save(Member.from(memberRequest)));
    }

    private void validateDuplicateMember(MemberRequest member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }

    //회원 한건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
