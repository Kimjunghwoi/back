package com.shop.back.controller.dto.request;


import com.shop.back.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequest {

    private String name;

    private Address address;



}
