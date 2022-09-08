package com.shop.back.controller;

import com.shop.back.domain.Member;
import com.shop.back.domain.item.Item;
import com.shop.back.service.ItemService;
import com.shop.back.service.MemberService;
import com.shop.back.service.OrderService;
import com.shop.back.domain.Member;
import com.shop.back.domain.Orders;
import com.shop.back.domain.item.Item;
import com.shop.back.repository.OrderSearch;
import com.shop.back.service.ItemService;
import com.shop.back.service.MemberService;
import com.shop.back.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        orderService.order(memberId, itemId, count);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Orders> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "/order/orderList";

    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
