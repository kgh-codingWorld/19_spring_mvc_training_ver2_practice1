package com.application.traningVer2.practice1.member.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.traningVer2.practice1.dto.MemberDTO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Value("${file.repo.path}")
	// 1. @Value : 외부 설정 파일(application.properties 또는 application.yml)에 정의된 값을 자바 클래스의 필드에 주입할 때 사용
	// 2. ${file.repo.path} : 프로퍼티 값 가져옴
	// 3. 스프링 컨테이너가 인스턴스화 될 때, 프로퍼티에 정의된 값을 기반으로 초기화됨
	private String fileRepositoryPath;

	// 메인페이지로 이동
	@GetMapping("/main") // localhost/member/main
	public String main() { 
		return "member/main"; // templates/member/main.html 파일로 포워딩
	}
	
	// 회원가입 페이지로 이동
	@GetMapping("/register") // localhost/member/register
	public String register() {
		return "member/register"; // templates/member/register.html 파일로 포워딩
	}
	
	// 회원가입 작업 처리용
	@PostMapping("/register")
	public String register(@ModelAttribute MemberDTO memberDTO) {
		
		
		
		return "redirect:/member/main";
	}
}
