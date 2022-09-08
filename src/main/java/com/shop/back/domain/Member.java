package com.shop.back.domain;

import com.shop.back.controller.dto.request.MemberRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();
    //하이버네이트가 영속화할때 위 방식으로 해야 나중에 set..어쩌구..뭐 문제가있다고함 관리측면에서
//    public Member() {
//        orders = new ArrayList<>();
//    }


    @Builder
    public Member(Long id, String name, Address address, List<Orders> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.orders = orders;
    }

    public static Member from(MemberRequest memberRequest) {
        return Member.builder()
                .name(memberRequest.getName())
                .address(memberRequest.getAddress())
                .build();
    }
}
