package com.greedy.togather.admin.settle.model.dto;


import com.greedy.togather.admin.order.model.dto.AdminPaymentDTO;

import lombok.Data;

@Data
public class AdminFundingDTO {
	
	/* 주문, 결제, 환불을 포함한 DTO */
	
	private String orderNo;
	private String userNo;
	private String projNo;
	
	private AdminPaymentDTO pay;
	private AdminUserDTO user;
}
