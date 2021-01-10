package com.project.delivery;

import com.project.delivery.member.MemberRepository;
import com.project.delivery.member.MemberService;
import com.project.delivery.member.MemberServiceImpl;
import com.project.delivery.member.MemoryMemberRepository;
import com.project.delivery.order.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository(){
        System.out.println("member 저장소 등록");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        System.out.println("할인정책 등록");
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService(){
        System.out.println("memberService 등록");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("orderService 등록");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
