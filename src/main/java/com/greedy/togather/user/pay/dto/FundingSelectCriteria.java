package com.greedy.togather.user.pay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FundingSelectCriteria {
	
	private int page;
	private int totalCount;
	private int limit;
	private int buttonAmount;
	private int fundMaxPage;
	private int fundStartPage;
	private int fundEndPage;
	private int fundStartRow;
	private int fundEndRow;
	private String searchCondition;
	private String searchValue;
}
