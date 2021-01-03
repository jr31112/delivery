package com.project.delivery.order;

import com.project.delivery.member.Grade;
import com.project.delivery.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        return member.getGrade() == Grade.VIP ? price * discountPercent / 100 : 0;
    }
}
