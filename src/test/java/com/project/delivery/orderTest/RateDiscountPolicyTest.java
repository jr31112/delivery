package com.project.delivery.orderTest;

import com.project.delivery.member.Grade;
import com.project.delivery.member.Member;
import com.project.delivery.order.RateDiscountPolicy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    public void VIPDiscountTest(){
        // given
        Member member = new Member(1L, "Enzo", Grade.VIP);

        // when
        int discountPrice = discountPolicy.discount(member, 10000);

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }

    @Test
    public void basicDiscountTest(){
        // given
        Member member = new Member(1L, "Enzo", Grade.BASIC);

        // when
        int discountPrice = discountPolicy.discount(member, 10000);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }

}
