package com.greedy.togather.admin.project.dto;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
public class AdminProjectPageDTO {
	
	private String projNo;
	private String makerNm;
	private String makerEmail;
	private String makerPhone;
	private String makerAddress;
	private String makerIntro;
	private Date fundingStartDate;
	private Date fundingEndDate;
	private int fundingGoal;
	private String projField;
	private String projNm;
	private String projSummary;
	private List<AdminFileDTO> AdminFileList;	
	private String projDetail;
	private List<AdminRewardDTO> adminRewardList;
	private int fundingAchive;
	private String projInfoTitle;
	private String projInfoBody;	
	private String projReview;
	private Date projRegDate;
	private Date projRejectDate;
	private Date projConfirmDate;
	private Date projStopDate;
	private String projStatus;	


}
