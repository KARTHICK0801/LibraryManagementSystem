package com.example.demo.serviceimplementation;

import com.example.demo.entity.Member;
import com.example.demo.repo.MemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(int id) {
        return memberRepository.findById(id);
    }

    @Override
    public void addMember(Member member) {
        member.setRegisteredDate(LocalDate.now()); // Set current date as registration date
        memberRepository.save(member);
    }

    @Override
    public void updateMember(int id, Member member) {
        member.setId(id); // Set the ID to ensure the correct member is updated
        memberRepository.update(member);
    }
    @Override
    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void deleteMember(int id) {
        memberRepository.deleteById(id);
    }
}
