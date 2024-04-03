package com.greedy.togather.user.main.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MainPageDTO {
	
	private String mpCode;		// 메인페이지에 게시된 프로젝트 코드
	private String projNo;		// 프로젝트 번호
	private Date mpStartDate;	// 시작일
	private Date mpEndDate;		// 종료일
	private String mpCategory;	// 분류
	private String mpStatus;	// 상태
	
}
