package com.greedy.togather.admin.user.model.dto;

import java.util.List;

import com.greedy.togather.admin.project.dto.AdminProjectDTO;

import lombok.Data;


@Data
public class AdminUserDTO {
	
	private String userNo;
	private String email;
	private String pwd;
	private String userNm;
	private String profileNm;
	private String phone;
	private String address;
	private java.util.Date joinDate;
	private java.util.Date quitDate;
	private String quitYn;
	private String userToken;
	
	private List<AdminFundingDTO> fundingList;
	private List<AdminProjectDTO> projList;
}
