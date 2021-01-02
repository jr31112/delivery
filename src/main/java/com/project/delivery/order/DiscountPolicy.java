package com.project.delivery.order;

import com.project.delivery.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
