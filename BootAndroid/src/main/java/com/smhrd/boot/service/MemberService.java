package com.smhrd.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.boot.model.AndMember;
import com.smhrd.boot.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository repository;
	
	// 회원 가입
	public AndMember join(AndMember am) {
		// insert
		return repository.save(am);
	}
	
	public AndMember login(AndMember am) {
		// select (where id=? and pw=?)
		return repository.findByIdAndPw(am.getId(), am.getPw());
	}
}
