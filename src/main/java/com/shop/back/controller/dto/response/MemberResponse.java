package com.shop.back.controller.dto.response;

import com.shop.back.domain.Address;
import com.shop.back.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;

@Getter
@Setter
public class MemberResponse {


    private Long id;

    private String name;

    private Address address;

    @Builder
    public MemberResponse(Long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .address(member.getAddress())
                .build();
    }
}
