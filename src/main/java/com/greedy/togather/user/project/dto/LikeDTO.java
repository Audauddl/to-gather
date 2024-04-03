package com.greedy.togather.user.project.dto;

import com.greedy.togather.user.user.model.dto.UserDTO;

import lombok.Data;

@Data
public class LikeDTO {

	private String likeNo;	// 좋아요 번호
	private String userNo;	// 회원 번호
	private String projNo;	// 프로젝트 번호
	
	private UserDTO user;	// 좋아요를 누른 회원의 정보
}
