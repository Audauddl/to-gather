package com.greedy.togather.user.pay.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class DeliveryDTO {
	
	private String orderNo;
	private String recipientNm;
	private String recipientAddress;
	private String recipientPhone;
	private String deliveryNm;
	private String invoiceNo;
	private Date deliveryStartDate;
	private Date buyFixedDate;
	private String deliveryStatus;
}
