package com.greedy.togather.admin.order.model.dto;

import lombok.Data;

@Data
public class AdminRefundDTO {

	private String payNo;
	private java.util.Date refundDate;
	private int refundPrice;
	private String refundReason;
}
