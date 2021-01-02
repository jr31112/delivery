package com.project.delivery.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}