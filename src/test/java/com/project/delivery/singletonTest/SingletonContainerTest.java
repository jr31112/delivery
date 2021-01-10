package com.project.delivery.singletonTest;

import com.project.delivery.AppConfig;
import com.project.delivery.member.MemberRepository;
import com.project.delivery.member.MemberServiceImpl;
import com.project.delivery.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

public class SingletonContainerTest {

    @Test
    @DisplayName("Stateful Service Not Allowed Test")
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA: A사용자 10000원 주문
        statefulService1.order("userA", 10000);

        // ThreadB: B사용자 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();

        // ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        System.out.println("price = " + price);
        // assertThat(statefulService1.getPrice()).isEqualTo(10000);
        assertThat(statefulService1.getPrice()).isNotEqualTo(10000);
    }

    @Test
    @DisplayName("AppConfig Test for Singleton Container")
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // 모두 같은 인스턴스를 참고하고 있다.
        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        // 모두 같은 인스턴스를 참고하고 있다.
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig도 스프링 빈으로 등록된다.
        AppConfig bean = ac.getBean(AppConfig.class);
        // 출력: bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$6e4766f1
        System.out.println("bean = " + bean.getClass());
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
