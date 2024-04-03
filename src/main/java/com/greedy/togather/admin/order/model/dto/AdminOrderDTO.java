package com.greedy.togather.admin.order.model.dto;

import lombok.Data;

@Data
public class AdminOrderDTO {

	private String orderNo;
	private String rewardNo;
	private String projNo;
	private String userNo;
	private java.util.Date orderDate;
	private int rewardQuantity;
	private int rewardPrice;
	private int extraReward;
	private int deliveryFee;
	private int dcPrice;
	private int payPrice;
	
	private AdminPaymentDTO pay;
}
