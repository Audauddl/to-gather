package com.greedy.togather.admin.settle.model.dto;

import lombok.Data;

@Data
public class AdminRewardDTO {

	private String rewardNo;
	private String projNo;
	private String rewardNm;
	private String rewardContent;
	private int rewardPrice;
	private int rewardFee;
	private java.util.Date rewardDueDate;
}