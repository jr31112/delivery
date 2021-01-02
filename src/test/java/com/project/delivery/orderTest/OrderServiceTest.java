package com.project.delivery.orderTest;

import com.project.delivery.member.*;
import com.project.delivery.order.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {

    private final MemberService memberService = new MemberServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrderTest(){
        // given
        Member member = new Member(1L, "Enzo", Grade.VIP);
        memberService.join(member);
        // when
        Order order = orderService.createOrder(member.getId(), "손톱깎이", 6000);

        // then
        assertThat(order.getItemName()).isEqualTo("손톱깎이");
        assertThat(order.getItemPrice()).isEqualTo(6000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}