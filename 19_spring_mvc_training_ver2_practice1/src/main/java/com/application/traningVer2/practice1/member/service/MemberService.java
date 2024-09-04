package com.application.traningVer2.practice1.member.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.application.traningVer2.practice1.member.dto.MemberDTO;

public interface MemberService {
	
	public void createMember(MultipartFile uploadProfile, MemberDTO memberDTO) throws IllegalStateException, IOException;
	public String checkValidId(String memberId);
	public boolean login(MemberDTO memberDTO);
	public MemberDTO getMemberDetail(String memberId); // 세부정보 가져오기
	public void updateMember(MultipartFile uploadProfile, MemberDTO memberDTO) throws IllegalStateException, IOException;
	
}
