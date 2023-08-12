package com.smhrd.shop.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.shop.converter.imageConverter;
import com.smhrd.shop.converter.imageToBase64;
import com.smhrd.shop.domain.Product;
import com.smhrd.shop.mapper.ProductMapper;

@Service
public class ProductService {

	@Autowired
	private ProductMapper mapper;
	
	@Autowired
	private ResourceLoader resourceLoader;
	// 특정 경로에 있는 파일을 가지고 오기
	
	// product 전체 정보 불러올 때 사용
	public JSONArray productList() {
		List<Product> list = mapper.productList();
		
		// list(Product-> img (파일이름만 가지고 있음, 실제 파일X)
		// Product -> img(파일이름-dress1.jpeg) -> 실제 파일 가지고 오기(static/img/...)
		// 파일을 응답해줄 때 (파일 형태 : byte형태로 변환 해야함!)
		// Product 의 img 필드 값을 이미지를 바이트 문자열 형태로 바꾼 걸로 수정
		
		// JsonArray 에 JsonObject가 들어있는 형식으로 응답
		// List -> JsonArray로 변환
		JSONArray jsonArray = new JSONArray();
		imageConverter<File, String> converter = new imageToBase64();
		
		// Product -> JsonObjet로 변환
		for(Product p : list ) {
			// 1. img 필드값 수정 ( 원래는 파일이름 -> 이미지 바이트 문자열 형태로 변경)
			// 1-1. 변환할 파일 실제 경로 정의
			// classpath : 자바경로와 리소스경로를 알아서 찾아줌
			// filePath : static/img/dress1.jpeg
			String filePath = "classpath:/static/img/"+p.getImg(); // 단순한 경로 -> ResourceLoader설정
			Resource resource = resourceLoader.getResource(filePath);	// 파일의 대한 메타데이터
			String fileStringValue = null; 
			try {
				 fileStringValue = converter.convert(resource.getFile()); // 바이트의 문자열 형태로 만듬, 예외처리 필요
			} catch (IOException e) {
				e.printStackTrace();
			} 
			// System.out.println(fileStringValue);
			p.setImg(fileStringValue);	// 변환된 값이 이미지필드로 
			
			// 2. Product 객체를 -> JsonObject(key:value)로 변환하기
			JSONObject obj =  new JSONObject();	// 비어있는 json object 생성
			obj.put("product",p);	// 비어있는 json object에 값을 추가
			
			jsonArray.add(obj);
		}
		return jsonArray;
	}
		


		public JSONObject productOne(String pcode) {
			Product p = mapper.productOne(pcode);
			imageConverter<File, String> converter = new imageToBase64();
			String filePath = "classpath:/static/img/"+p.getImg(); // 단순한 경로 -> ResourceLoader설정
			Resource resource = resourceLoader.getResource(filePath);	// 파일의 대한 메타데이터
			String fileStringValue = null; 
			try {
				 fileStringValue = converter.convert(resource.getFile()); // 바이트의 문자열 형태로 만듬, 예외처리 필요
			} catch (IOException e) {
				e.printStackTrace();
			} 
			// System.out.println(fileStringValue);
			p.setImg(fileStringValue);	// 변환된 값이 이미지필드로 
			JSONObject obj = new JSONObject();
			obj.put("product",p);
			
			return obj;
			

		}
	}

