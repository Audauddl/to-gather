package com.greedy.togather.user.pay.dto;

import java.io.Serializable;
import java.sql.Date;

import com.greedy.togather.user.project.dto.ProjectDTO;
import com.greedy.togather.user.project.dto.RewardDTO;

import lombok.Data;

@Data
public class PayOrderDTO implements Serializable{
	
	private String orderNo;
	private String userNo;
//	@JsonFormat(pattern = "yy/MM/dd")
//	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yy/MM/dd", timezone="Asia/Seoul")
	private Date orderDate;
	private int rewardQuantity;
	private int rewardPrice;
	private int extraReward;
	private int deliveryFee;
	private int dcPrice;
	private int payPrice;
	private RewardDTO reward;
	private DeliveryDTO delivery;
	private PaymentDTO payment;
	private ProjectDTO project;
}
