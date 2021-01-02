package com.project.delivery.memberTest;

import com.project.delivery.member.Grade;
import com.project.delivery.member.Member;
import com.project.delivery.member.MemberService;
import com.project.delivery.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
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