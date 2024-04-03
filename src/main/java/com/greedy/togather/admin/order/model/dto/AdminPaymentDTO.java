package com.greedy.togather.admin.order.model.dto;

import lombok.Data;

@Data
public class AdminPaymentDTO {
	
	private String payNo;
	private String orderNo;
	private String payMethod;
	private int payPrice;
	private java.util.Date payDate;
	
	private AdminRefundDTO refund;
}
