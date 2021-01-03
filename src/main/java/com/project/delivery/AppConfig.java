package com.project.delivery;

import com.project.delivery.member.MemberRepository;
import com.project.delivery.member.MemberService;
import com.project.delivery.member.MemberServiceImpl;
import com.project.delivery.member.MemoryMemberRepository;
import com.project.delivery.order.*;

public class AppConfig {

    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
    }

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
