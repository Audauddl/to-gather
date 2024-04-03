package com.greedy.togather.admin.user.model.dto;


import com.greedy.togather.admin.order.model.dto.AdminPaymentDTO;
import com.greedy.togather.admin.order.model.dto.AdminRewardDTO;
import com.greedy.togather.admin.project.dto.AdminProjectDTO;

import lombok.Data;

@Data
public class AdminFundingDTO {
	
	private String orderNo;
	private String userNo;
	
	private AdminPaymentDTO pay;
	private AdminProjectDTO proj;
	private AdminRewardDTO reward;


}
