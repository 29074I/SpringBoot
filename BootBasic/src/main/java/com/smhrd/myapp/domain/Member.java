package com.smhrd.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor	// 전부 초기화 시켜주는 생성자
@NoArgsConstructor	// 기본생성자
@Getter	// Get
@Setter	// Set
public class Member {
	private String id;
	private String pw;
	private String nick;
	
	
}
