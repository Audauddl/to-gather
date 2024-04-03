package com.greedy.togather.user.pay.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.togather.user.pay.dao.PaymentMapper;
import com.greedy.togather.user.pay.dto.FundingPagenation;
import com.greedy.togather.user.pay.dto.FundingSelectCriteria;
import com.greedy.togather.user.pay.dto.PayOrderDTO;
import com.greedy.togather.user.pay.dto.PaymentDTO;
import com.greedy.togather.user.pay.dto.RefundDTO;
import com.greedy.togather.user.project.dto.ProjectDTO;
import com.greedy.togather.user.project.dto.RewardDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class PaymentService {
	
	private final PaymentMapper paymentMapper;
	
	public PaymentService(PaymentMapper paymentMapper) {
		this.paymentMapper = paymentMapper;
	}
	
	public void registOrder(PayOrderDTO order) /*throws orderRegistException*/{
		int result1 = paymentMapper.registOrder(order);
		
//		if(!(result1 > 0 )) {
//			throw new orderRegistException("결제에 실패하였습니다.");
//		}
	}

	public void registDelivery(PayOrderDTO order) {
		
		int result2 = paymentMapper.registDelivery(order);		
	}

	public void registPayment(PayOrderDTO order) {
		
		int result3 = paymentMapper.registPayment(order);
		
	}

	public void updatefundingAchive(PayOrderDTO order) {
		
		int result4 = paymentMapper.updatefundingAchive(order);
	}

//	public String selectPayment(PaymentDTO payment) {
//		
//		String result5 = paymentMapper.selectPayment(payment);
//		
//		return paymentMapper.selectPayment(payment);
//	}

	public Map<String, Object> slectPayment(String payNo) {
		
		PaymentDTO payment = paymentMapper.selectPayment(payNo);
		
		Map<String, Object> paymentList = new HashMap<>();
		
		paymentList.put("payment", payment);
				
		return  paymentList;
	}

	public Map<String, Object> searchPayScreen(String projNo) {
		
		ProjectDTO projectNo = paymentMapper.selectProjectDetail(projNo);
//		RewardDTO reward = paymentMapper.selectRewardList(rewardNo);
		
		Map<String, Object> payScreenDetails = new HashMap<>();
		payScreenDetails.put("projectNo", projectNo);
//		payScreenDetails.put("reward", reward);
		
		return payScreenDetails;
	}

	public Map<String, Object> searchRewardScreen(String rewardNo) {
		RewardDTO reward = paymentMapper.selectRewardList(rewardNo);
		Map<String, Object> payScreenDetails = new HashMap<>();
		payScreenDetails.put("reward", reward);
		return payScreenDetails;
	}

	public Map<String, Object> slectFundList(String userNo, int page) {
		
		//전체 후원 수 
		int totalCount = paymentMapper.selectFundList1(userNo);
		
		int limit = 5;
		
		int buttonAmount = 5;
		
		FundingSelectCriteria fundingSelectCriteria = FundingPagenation.getSelectCriteria(page, totalCount, limit, buttonAmount);
		log.info("[PaymentService] fundingSelectCriteria : {}", fundingSelectCriteria);
		
//		List<PayOrderDTO> fundNo = paymentMapper.selectFundList(No);
//		
//		Map<String, Object> fundList = new HashMap<>();
//		fundList.put("fundNo", fundNo);
		
		List<PayOrderDTO> fundAndPaging = paymentMapper.selectFundList(fundingSelectCriteria, userNo);
		
		Map<String, Object> fundList = new HashMap<>();
		fundList.put("paging", fundingSelectCriteria);
		fundList.put("fundNo", fundAndPaging);
		
		
		return fundList;
	}

//	public Map<String, Object> slectPaymentFefund(String orderNo) {
//		
//		List<PayOrderDTO> rePayment = paymentMapper.slectPaymentFefund(orderNo);
//		
//		log.info("orderNo ::::::::::::" + orderNo);
//		System.out.println(rePayment);
//		
//		Map<String, Object> rePaymentList = new HashMap<>(); 
//		rePaymentList.put("rePayment", rePayment);
//		
//		return rePaymentList;
//	}

	public Map<String, Object> selectRefund(String userNo) {
		
		//페이징을 위한 전체 후원 검색 코드
		List<RefundDTO> refund = paymentMapper.selectRefund(userNo);
		//기존 코드
//		List<RefundDTO> refund = paymentMapper.selectRefund(userNo);
		
		// 한 페이지에 보여줄 게시물의 수
		int limit = 5;
		// 한 번에 보여질 페이징 버튼의 수
		int buttonAmount = 10;
		
		
		
		Map<String, Object> refundList = new HashMap<>();
		
		refundList.put("refund", refund);
		
		return refundList;
	}

	public Map<String, Object> selectFund(String orderNo) {
		
		PayOrderDTO rePayment = paymentMapper.selectFund(orderNo);
		
		log.info("orderNo ::::::::::::" + orderNo);
		System.out.println(rePayment);
		
		Map<String, Object> rePaymentList = new HashMap<>(); 
		rePaymentList.put("rePayment", rePayment);
		
		return rePaymentList;
	}
	
	public void insertRefund(PaymentDTO cancel) {
		System.out.println("환불환불" + cancel);
		int result7 = paymentMapper.insertRefund(cancel);
	}








//	public Map<String, Object> searchPayScreen(Map<String, Object> payScreenDetails) {
//		
//		ProjectDTO projectDetails = paymentMapper.selectProjectDetail(payScreenDetails);
//		log.info("[PaymentService] projectDetail : {}", projectDetails);
//		
//		/* 리워드 조회 */
//		List<RewardDTO> rewardLists = paymentMapper.selectRewardList(payScreenDetails);
//		log.info("[PaymentService] rewardList : {}", rewardLists);
//		
//		Map<String, Object> payScreenDetails = new HashMap<>();
//		payScreenDetails.put("projectDetail", projectDetails);
//		payScreenDetails.put("rewardList", rewardLists);
//		
//		return payScreenDetails;
//	}
	
	
}


