package com.greedy.togather.user.pay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.greedy.togather.user.pay.dto.FundingSelectCriteria;
import com.greedy.togather.user.pay.dto.PayOrderDTO;
import com.greedy.togather.user.pay.dto.PaymentDTO;
import com.greedy.togather.user.pay.dto.RefundDTO;
import com.greedy.togather.user.project.dto.ProjectDTO;
import com.greedy.togather.user.project.dto.RewardDTO;

@Mapper
public interface PaymentMapper {

	

	int registOrder(PayOrderDTO order);

	int registDelivery(PayOrderDTO order);

	int registPayment(PayOrderDTO order);

	int updatefundingAchive(PayOrderDTO order);

	PaymentDTO selectPayment(String payNo);

	ProjectDTO selectProjectDetail(String projNo);

	RewardDTO selectRewardList(String rewardNo);

//	List<PayOrderDTO> selectFundList(String No);

//	List<PayOrderDTO> slectPaymentFefund(String orderNo);

	List<RefundDTO> selectRefund(String userNo);

	PayOrderDTO selectFund(String orderNo);

	int insertRefund(PaymentDTO cancel);

	int selectFundList1(String userNo);

	List<PayOrderDTO> selectFundList(FundingSelectCriteria fundingSelectCriteria, String userNo);



}
