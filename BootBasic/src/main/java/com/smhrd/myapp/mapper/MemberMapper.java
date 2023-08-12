package com.smhrd.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.myapp.domain.Member;

@Mapper
public interface MemberMapper {
	// service에서 호출해서 사용할
	// 1. 어노테이션으로 사용하는 방법
	// 2. xml파일을 이용하는 방법도 있삼 -> resources 폴더에서(resource.com.myapp.mapper.
	public List<Member> memberList();
}
