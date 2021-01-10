package com.project.delivery;

import com.project.delivery.member.MemberService;
import com.project.delivery.order.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);
	}

//	public static void main(String[] args) {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
//        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
//        OrderService orderService2 = applicationContext.getBean("orderService", OrderService.class);
//
//        System.out.println(memberService);
//        System.out.println(orderService);
//        System.out.println(orderService2);
//
//    }

}
