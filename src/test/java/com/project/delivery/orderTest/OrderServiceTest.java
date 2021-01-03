package com.project.delivery.orderTest;

import com.project.delivery.AppConfig;
import com.project.delivery.member.*;
import com.project.delivery.order.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {

    private MemberService memberService;
    private OrderService orderService;

    @Test
    public void createOrderTest(){
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

    @BeforeEach
    public void setUp(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
}