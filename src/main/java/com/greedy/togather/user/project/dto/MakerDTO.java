package com.greedy.togather.user.project.dto;

import lombok.Data;

@Data
public class MakerDTO {
	
	private String projNo;				// 프로젝트 번호
	private String makerProfileName;	// 프로필 파일
	private String makerName;			// 이름
	private String makerAddress;		// 주소
	private String makerPhone;			// 휴대폰 번호
	private String makerEmail;			// 이메일
	private String makerIntro;			// 소개
	
}
