package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Get all members
    @GetMapping("get")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    // Get a specific member by ID
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable int id) {
        return memberService.getMemberById(id);
    }

    // Add a new member
    @PostMapping("/save")
    public ResponseEntity<Void> saveMember(@RequestBody Member member) {
        memberService.saveMember(member);
        return ResponseEntity.ok().build();
    }

    // Update an existing member
    @PutMapping("/{id}")
    public void updateMember(@PathVariable int id, @RequestBody Member member) {
        memberService.updateMember(id, member);
    }

    // Delete a member by ID
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable int id) {
        memberService.deleteMember(id);
    }
}
