package com.greedy.togather.user.pay.dto;

import java.sql.Date;

import com.greedy.togather.user.project.dto.ProjectDTO;

import lombok.Data;

@Data
public class PaymentDTO {
	
	private String payNo;
	private String orderNo;
	private String payMethod;
	private int payPrice;
//	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date payDate;
//	private ProjectDTO projName;
	
}
