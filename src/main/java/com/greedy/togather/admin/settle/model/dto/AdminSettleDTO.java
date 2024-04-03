package com.greedy.togather.admin.settle.model.dto;

import lombok.Data;

@Data
public class AdminSettleDTO {

	private String settleNo;
	private String projNo;
	private int totalFundingPrice;
	private int totalFee;
	private int vat;
	private int donation;
	private int finalPrice;
	private java.util.Date settleDate;
	private String settleStatus;
	
	private AdminProjectDTO proj;
}