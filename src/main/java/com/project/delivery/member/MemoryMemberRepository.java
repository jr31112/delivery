package com.project.delivery.member;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> rep = new ConcurrentHashMap<>();

    @Override
    public void save(Member member) {
        rep.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return rep.get(memberId);
    }
}
