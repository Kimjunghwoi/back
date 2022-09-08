package com.shop.back.service;

import com.shop.back.domain.Delivery;
import com.shop.back.domain.Member;
import com.shop.back.domain.OrderItem;
import com.shop.back.domain.Orders;
import com.shop.back.domain.item.Item;
import com.shop.back.repository.ItemRepository;
import com.shop.back.repository.MemberRepository;
import com.shop.back.repository.OrderRepository;
import com.shop.back.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Orders order = Orders.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Orders orders = orderRepository.findOne(orderId);
        //주문 취소
        orders.cancel();

    }


    //검색
    public List<Orders> findOrders(OrderSearch orderSearch) {

        return orderRepository.findAllByString(orderSearch);
    }

}
