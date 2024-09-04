package com.application.traningVer2.practice1.member.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberDTO {

	private String memberId; 		// 아이디
	private String passwd;			// 비밀번호
	private String profileOriginalName;	// 프로필이름
	private String profileUUID;		// 프로필 UUID
	private String memberNm;		// 이름
	private String sex;				// 성별
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthAt;			// 생일
	private String hp;				// 휴대전화
	private String smsstsYn;			// 메시지 수신 여부
	private String email;			// 이메일
	private String emailstsYn;			// 이메일 수신 여부
	private String zipcode;			// 우편번호
	private String roadAddress;		// 도로명
	private String jibunAddress;		// 지번
	private String namujiAddress;		// 나머지주소
	private String etc;				
	private String activeYn;		
	private Date inactiveAt;
	private Date joinAt;
}
