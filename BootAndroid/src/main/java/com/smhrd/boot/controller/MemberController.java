package com.smhrd.boot.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smhrd.boot.model.AndMember;
import com.smhrd.boot.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// localhost:8089/join으로 요청이 들어왔을 때
	@PostMapping("/join")
	public String join(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String andMember = request.getParameter("AndMember");
//		System.out.println(andMember);
		ObjectMapper om = new ObjectMapper();	// jackson-databind에서 제공
		AndMember am = om.readValue(andMember, AndMember.class);
		AndMember result =  service.join(am);
		if(result!=null) {
			return "Success";
		}else {
			return "Fail";
		}
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String andMember = request.getParameter("AndMember");
		
		ObjectMapper om = new ObjectMapper();
		AndMember am = om.readValue(andMember, AndMember.class);
		
		// Request -> Controller(요청, 응답) -> Service(요청과 응답사이의 로직 처리)
		// Mybatis : -> Mapper (interface)
		// JPA     : -> Repository (interface)
		AndMember result = service.login(am);
		
		System.out.println(result);
		
		if(result!=null) {
			return "Success";
		}else {
			return "Fail";
		}
	}
	

}
