package com.greedy.togather.user.project.dto;

import lombok.Data;

@Data
/* 리워드 조회 시, 리워드 마다의 참여인원을 함께 조회하기 위한 orderDTO */
public class OrderDTO {
	
	private String orderNo;
	private String rewardNo;
	
}
