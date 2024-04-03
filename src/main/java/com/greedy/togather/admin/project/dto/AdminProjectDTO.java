package com.greedy.togather.admin.project.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class AdminProjectDTO {
	
	private String projNo;
	private String categoryNo;
	private String userNo;
	private String projNm;
	private String projSummary;
	private String projDetail;
	private String projInfoTitle;
	private String projInfoBody;
	private Date fundingStartDate;
	private Date fundingEndDate;
	private int fundingGoal;
	private int fundingAchive;
	private Date projRegDate;
	private Date projRejectDate;
	private Date projConfirmDate;
	private Date projStopDate;
	private String projStatus;
	private String projReview;
	

}
