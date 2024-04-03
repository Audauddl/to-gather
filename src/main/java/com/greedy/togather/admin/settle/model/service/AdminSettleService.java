package com.greedy.togather.admin.settle.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.togather.admin.settle.model.dao.AdminSettleMapper;
import com.greedy.togather.admin.settle.model.dto.AdminFundingDTO;
import com.greedy.togather.admin.settle.model.dto.AdminProjectDTO;
import com.greedy.togather.admin.settle.model.dto.AdminSettleDTO;

import com.greedy.togather.common.paging.Pagenation;
import com.greedy.togather.common.paging.SelectCriteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AdminSettleService {

	
private final AdminSettleMapper adminSettleMapper;
	
	public AdminSettleService(AdminSettleMapper adminSettleMapper) {
		this.adminSettleMapper = adminSettleMapper;
	}
	
	public Map<String, Object> selectSettleList(Map<String, String> searchMap, int page) {
			
		int totalCount = adminSettleMapper.selectTotalCount(searchMap);
		log.info("[AdminSettleService] totalCount : {}", totalCount);
		
		int limit = 10;
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
		log.info("[AdminSettleService] selectCriteria : {}", selectCriteria);
		
		List<AdminSettleDTO> settleList = adminSettleMapper.selectSettleList(selectCriteria);
		log.info("[AdminSettleService] settleList : {}", settleList);
		
		Map<String, Object> settleListAndPaging = new HashMap<>();
		settleListAndPaging.put("paging", selectCriteria);
		settleListAndPaging.put("settleList", settleList);
		
		return settleListAndPaging;
	}

	public void deleteSettleChecked(AdminSettleDTO settle) {

		adminSettleMapper.deleteSettleChecked(settle);
		
	}

	
	public Map<String, Object> selectSettleDetail(String settleNo) {
		
		/* 프로젝트 정보 조회*/
		AdminSettleDTO projInfo = adminSettleMapper.selectProjInfo(settleNo);
		log.info("[AdminSettleService] projInfo : {}", projInfo);
		
		/* 펀딩 내역 조회*/
		String projNo = projInfo.getProjNo();
		log.info("[AdminSettleService] projNo : {}", projInfo.getProjNo());
		
		List<AdminFundingDTO> fundingInfo = adminSettleMapper.selectFundingInfo(projNo);
		log.info("[AdminSettleSerivce] fundingInfo : {}", fundingInfo);

		/* 총 펀딩 금액 계산 */
		int totalPay = 0; // 총 결제금액
		int totalRefund = 0; // 총 환불 금액
		
		for(AdminFundingDTO funding : fundingInfo) {
			totalPay += funding.getPay().getPayPrice();
			totalRefund += funding.getPay().getRefund().getRefundPrice();	
		}
		
		int totalFunding = totalPay - totalRefund; // 총 펀딩금액
		int mnCharge = (int) (totalFunding * 0.07); // 운영 수수료
		int payCharge = (int) (totalFunding * 0.03); // 결제 수수료
		
		Map<String, Object> settleInfo = new HashMap<>();		
		settleInfo.put("projInfo", projInfo);
		settleInfo.put("fundingInfo", fundingInfo);
		settleInfo.put("totalPay", totalPay);
		settleInfo.put("totalRefund", totalRefund);
		settleInfo.put("totalFunding", totalFunding);
		settleInfo.put("mnCharge", mnCharge);
		settleInfo.put("payCharge", payCharge);
	
		return settleInfo;		
	}


	public void doSettle(String projNo) {			
		adminSettleMapper.doSettle(projNo);
	}

	public List<AdminProjectDTO> selectEndProject() {
		return adminSettleMapper.selectEndProject();
	}

	public void insertTblSettle(String projNo) {
		adminSettleMapper.insertTblSettle(projNo);
		
	}








}