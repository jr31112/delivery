package com.project.delivery.memberTest;

import com.project.delivery.AppConfig;
import com.project.delivery.member.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {

    private MemberService memberService;

    @BeforeEach
    public void setUp(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    public void join(){
        // given
        Member member = new Member(1L,"Enzo", Grade.VIP);

        // when
        memberService.join(member);
        Member testMember = memberService.findMember(1L);

        // then
        assertThat(testMember.getName()).isEqualTo(member.getName());
        assertThat(testMember.getGrade()).isEqualTo(member.getGrade());
    }
}