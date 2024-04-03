package com.greedy.togather.admin.orderManage.model.dto;

import com.greedy.togather.user.project.dto.MakerDTO;
import com.greedy.togather.user.user.model.dto.UserDTO;

import lombok.Data;

// 주문 정보를 나타내는 DTO 클래스
@Data
public class AdminOrderManageDTO {

		private String orderNo;
		private String userNo;
		private String projNo;
		private String rewardNo;
		private java.util.Date orderDate;
		private int rewardQuantity;
		private int rewardPrice;
		private int extraReward;
		private int deliveryFee;
		private int dcPrice;
		private int payPrice;
		private String projNm;
		private String rewardNm;
		private String email;
		private String phone;
		private String address;
		private String userNm;
		
		private String makerNm;
		private String makerEmail;
		private String makerPhone;
		private String makerAddress;
		private String makerIntro;			// 소개
		
		
		
		//상세 정보 조회용도
		//MAKER DTO
		private MakerDTO maker;

		
		//USER(SUPPOTER) DTO
		private UserDTO supporter;
		
		
		
	}
