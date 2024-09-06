package com.example.demo.service;

import com.example.demo.entity.Member;
import java.util.List;

public interface MemberService {
    List<Member> getAllMembers();
    Member getMemberById(int id);
    void addMember(Member member);
    void updateMember(int id, Member member);
    void deleteMember(int id);
    void saveMember(Member member);
}
