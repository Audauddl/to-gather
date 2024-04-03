package com.greedy.togather.user.project.dto;

import java.sql.Date;
import java.util.List;

import com.greedy.togather.user.main.dto.MainPageDTO;
import com.greedy.togather.user.user.model.dto.UserDTO;

import lombok.Data;

@Data
public class ProjectDTO {
	
	private String projNo;			// 프로젝트번호
	private String categoryNo;		// 카테고리번호
	private String userNo;			// 회원번호
	private String projName;		// 프로젝트명
	private String projSummary;		// 프로젝트 간략 소개
	private String projDetail;		// 프로젝트 상세 소개
	private String projInfoTitle;	// 리워드 안내 제목
	private String projInfoBody;	// 리워드 안내 내용
	private Date fundingStartDate;	// 펀딩 시작일
	private Date fundingEndDate;	// 펀딩 종료일
	private int fundingGoal;		// 펀딩 목표 금액
	private int fundingAchive;		// 펀딩 달성 금액
	private String projStatus;		// 상태
	private String projReview;		// 후기 내용
	private int leftDays;			// TRUNC(FUNDING_END_DATE - SYSDATE) 남은 날짜
	private int percent;			// TRUNC(FUNDING_ACHIVE / FUNDING_GOAL * 100) 현재 펀딩 달성율
	private int totalLikes;			// COUNT(LIKE_NO) 총 좋아요 개수
	
	private CategoryDTO category;		// 카테고리 정보
	private List<LikeDTO> likeList; 	// 좋아요 정보
	private List<ReplyDTO> replyList; 	// 댓글 정보
	private MakerDTO maker;				// 메이커 정보
	private List<FileDTO> fileList;		// 이미지 정보
	private List<RewardDTO> rewardList;	// 리워드 정보
	private UserDTO writer;				// 프로젝트 작성자 정보 
	private MainPageDTO mainPage;		// 메인 페이지 정보
	
	private FileDTO processedMakerProfile;			// 메이커 프로필
	private FileDTO processedMainImage;				// 대표 사진
	private List<FileDTO> processedSubImageList;	// 서브 사진(List)
	private FileDTO processedSettleDoc;				// 정산 서류
	private FileDTO processedAccountDoc;			// 통장 사본
	private FileDTO processedEtcDoc;				// 기타 서류
	
	
	/* 좋아요한 프로젝트 조회 */
	private LikeDTO likeDTO;
	private UserDTO userDTO;
	private FileDTO fileDTO;
}
