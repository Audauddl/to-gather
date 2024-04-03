package com.greedy.togather.user.pay.dto;

import com.greedy.togather.user.project.dto.ProjectDTO;
import com.greedy.togather.user.user.model.dto.UserDTO;

import lombok.Data;

@Data
public class RefundDTO {

	private String payNo;
	private String refundDate;
	private String refundPrice;
	private String refundReason;
	private String refundStatus;
	private UserDTO userNo;
	private PayOrderDTO orderNo;
	private ProjectDTO projNo;
	private ProjectDTO projName;
}
