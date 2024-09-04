package com.application.traningVer2.practice1.member.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.traningVer2.practice1.member.dao.MemberDAO;
import com.application.traningVer2.practice1.member.dto.MemberDTO;

// 항시 데이터가 비었는지 확인하는 로직이 필요함

@Service
public class MemberServiceImpl implements MemberService {
	
	// 외부 설정 파일 사용
	@Value("${file.repo.path}")
	private String fileRepositoryPath;
	
	// DAO에 있는 로직 사용
	@Autowired
	private MemberDAO memberDAO;
	
	// 비밀번호 암호화
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 로그 사용
	private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Override
	public void createMember(MultipartFile uploadProfile, MemberDTO memberDTO) throws IllegalStateException, IOException {
		
		// About File Uploads
		if(!uploadProfile.isEmpty()) { // 업로드된 파일이 있으면 
			String originalFileName = uploadProfile.getOriginalFilename(); // 해당 파일의 이름을 가져와 변수에 담음
			memberDTO.setProfileOriginalName(originalFileName); // 그 변수를 DTO에 저장
			
			String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 확장자 구하기
			
			String uploadFile = UUID.randomUUID() + extension; // 랜덤으로 UUID를 만들어 확장자와 합치고 변수에 담음
			memberDTO.setProfileUUID(uploadFile); // 그 변수를 DTO에 저장
		
			uploadProfile.transferTo(new File(fileRepositoryPath + uploadFile)); // 새로운 파일명으로 최종 업데이트
		
		}
		
		// About Status Y or N
		if(memberDTO.getSmsstsYn() == null) memberDTO.setSmsstsYn(fileRepositoryPath);
		if(memberDTO.getEmailstsYn() == null) memberDTO.setEmail("n");
		
		// About Password
		memberDTO.setPasswd(passwordEncoder.encode(memberDTO.getPasswd())); // memberDTO에 저장된 비밀번호를 인코딩하여 다시 저장
		
		memberDAO.createMember(memberDTO);
	}

	@Override
	public String checkValidId(String memberId) {
		
		String isValidId = "n"; // 초기값을 n으로 설정
		if(memberDAO.checkValidId(memberId) == null) { // checkValidId()에서 반환값이 null이면
			isValidId = "y"; // y로 변경
		}
		return isValidId;
	}

	@Override
	public boolean login(MemberDTO memberDTO) {
		
		MemberDTO validateData = memberDAO.login(memberDTO.getMemberId()); // memberId를 이용하여 비밀번호와 활동여부 데이터를 가져옴
		
		if(validateData != null) { // null이 아니면(조회된 회원정보가 있으면)
			
									   // 사용자가 입력한 비밀번호  // 데이터베이스에 저장된 비밀번호 //그리고 		// 계정활성 여부
			if(passwordEncoder.matches(memberDTO.getPasswd(),       validateData.getPasswd())           &&    		validateData.getActiveYn().equals("y")) {
				return true; // 로그인 성공
			}
		}
		return false; // 로그인 실패
	}

	@Override
	public MemberDTO getMemberDetail(String memberId) {
		return memberDAO.getMemberDetail(memberId);
		
	}
	
	@Override
	public void updateMember(MultipartFile uploadProfile, MemberDTO memberDTO) throws IllegalStateException, IOException {
		
		if(!uploadProfile.isEmpty()) {
			
			new File(fileRepositoryPath + memberDTO.getProfileUUID()).delete(); // 기존 파일 삭제
			
			String originalFilename = uploadProfile.getOriginalFilename(); // 원본파일명 가져오기
			memberDTO.setProfileOriginalName(originalFilename);
			
			String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
			
			String uploadFile = UUID.randomUUID() + extension;
			memberDTO.setProfileUUID(uploadFile);
			
			uploadProfile.transferTo(new File(fileRepositoryPath + uploadFile));
		}
		
		memberDAO.updateMember(memberDTO);
		
	}

}
