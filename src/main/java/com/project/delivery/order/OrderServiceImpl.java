package com.project.delivery.order;

import com.project.delivery.member.Member;
import com.project.delivery.member.MemberRepository;
import com.project.delivery.member.MemoryMemberRepository;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
