package com.greedy.togather.admin.mainBanner.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.greedy.togather.admin.project.dto.AdminFileDTO;

import lombok.Data;

@Data
public class AdminBannerDTO {
	
	private String mpCode;
	private String projNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mpStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mpEndDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mpRegDate;
	private String mpCategory;
	private String mpStatus;
	private List<AdminFileDTO> AdminFileList;
	
	
}
