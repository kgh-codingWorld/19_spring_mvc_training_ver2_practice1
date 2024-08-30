package com.application.traningVer2.practice1.member.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.application.traningVer2.practice1.dto.MemberDTO;
import com.application.traningVer2.practice1.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Value("${file.repo.path}")
	// 1. @Value : 외부 설정 파일(application.properties 또는 application.yml)에 정의된 값을 자바 클래스의 필드에 주입할 때 사용
	// 2. ${file.repo.path} : 프로퍼티 값 가져옴
	// 3. 스프링 컨테이너가 인스턴스화 될 때, 프로퍼티에 정의된 값을 기반으로 초기화됨
	private String fileRepositoryPath;
	
	@Autowired
	private MemberService memberService;

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
							// 하나의 값만 전달 받으므로 @RequestParam 사용
	public String register(@RequestParam("uploadProfile") MultipartFile uploadProfile,
							// 여러 개의 값을 form으로 전달 받으므로 @ModelAttribute 사용
							@ModelAttribute MemberDTO memberDTO) throws IllegalStateException, IOException {
		
		memberService.createMember(uploadProfile, memberDTO);
		
		return "redirect:/member/main";
	}
	
	// 아이디 중복 체크용
	@PostMapping("/validId")
	@ResponseBody
	public String validId(@RequestParam("memberId") String memberId) {
		
		return memberService.checkValidId(memberId);
	}
	
	// 로그인 페이지로 이동
	@GetMapping("/login") // localhost/member/login
	public String login() {
		return "member/login"; // templates/member/login.html 파일로 포워딩
	}
	
	// 로그인 작업 처리용
	@PostMapping("login")
						// 여러 개의 값을 ajax로 전달 받으므로 @RequestBody 사용
	public String login(@RequestBody MemberDTO memberDTO, 
						// session을 사용하기 위한 파라미터
						HttpServletRequest request) {
		
		String isValidMember = "n"; // "n"으로 초기값 설정
		
		if(memberService.login(memberDTO)) { // 회원정보 검색 처리하여 true 또는 false를 반환 > true일 때
			
			HttpSession session = request.getSession(); // 세션 생성
			session.setAttribute("memberId", memberDTO.getMemberId()); // 세션에 memberId 저장
			
			isValidMember = "y";
		}
		
		return isValidMember; // "y" 반환
	}
	
	// 로그아웃 페이지로 이동
	@GetMapping("/logout") // localhost/member/logout
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate(); // 세션 삭제
		
		return "redirect:/member/main"; // /member/main으로 포워딩
	}
	
	// 회원정보 수정 페이지로 이동
	@GetMapping("/update") //localhost/member/update
	public String update() {
		return "member/update";
	}
	
	// 회원정보 수정 작업 처리용
	@PostMapping("/update")
	public String update(@RequestParam("uploadProfile") MultipartFile uploadProfile, @ModelAttribute MemberDTO memberDTO) throws IllegalStateException, IOException {
		
		memberService.updateMember(uploadProfile, memberDTO);
		
		return "redirect:/member/main";
	}
}
