package com.greedy.togather.user.project.dto;

import lombok.Data;

@Data
public class FileDTO {

	private String fileNo;				// 첨부파일 번호
	private String projNo;				// 프로젝트 번호
	private String fileType;			// 첨부파일 유형
	private String originalFileName;	// 원본 파일명
	private String savedFileName;		// 저장 파일명
	private String filePath;			// 파일 경로
	private String thumPath;			// 썸네일 경로
	
}
