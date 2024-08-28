package com.application.traningVer2.practice1.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MemberDTO {

	private String memberId; 		// 아이디
	private String passWd;			// 비밀번호
	private String profOrigName;	// 프로필이름
	private String profUUID;		// 프로필 UUID
	private String memberNm;		// 이름
	private String sex;				// 성별
	private Date birthAt;			// 생일
	private String hp;				// 휴대전화
	private String smsYn;			// 메시지 수신 여부
	private String email;			// 이메일
	private String emailYn;			// 이메일 수신 여부
	private String zipCode;			// 우편번호
	private String roadAddr;		// 도로명
	private String jibunAddr;		// 지번
	private String namujiAddr;		// 나머지주소
	private String etc;				
	private String activeYn;		
	private Date inacticeAt;
	private Date joinAt;
}
