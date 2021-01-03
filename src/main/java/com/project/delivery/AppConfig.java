package com.project.delivery;

import com.project.delivery.member.MemberService;
import com.project.delivery.member.MemberServiceImpl;
import com.project.delivery.member.MemoryMemberRepository;
import com.project.delivery.order.DiscountPolicy;
import com.project.delivery.order.FixDiscountPolicy;
import com.project.delivery.order.OrderService;
import com.project.delivery.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
