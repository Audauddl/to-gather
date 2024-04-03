package com.greedy.togather.admin.orderManage.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.togather.admin.orderManage.model.dao.AdminOrderManageMapper;
import com.greedy.togather.admin.orderManage.model.dto.AdminOrderManageDTO;
import com.greedy.togather.common.paging.Pagenation;
import com.greedy.togather.common.paging.SelectCriteria;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AdminOrderManageService {
	
	  // 매퍼 클래스를 주입
    @Autowired
    private AdminOrderManageMapper adminOrderManageMapper;
    public AdminOrderManageService (AdminOrderManageMapper adminOrderManageMapper) {
		this.adminOrderManageMapper = adminOrderManageMapper;
    }
    
    
	public Map<String, Object> selectOrderManage1 (Map<String, String> searchMap, int page) {

    
		/* 1. 전체 게시글 수 확인 (검색어가 없는 경우, 있는 경우 모두 포함) => 페이징 처리 계산을 위해서 */
		int totalCount = adminOrderManageMapper.selectTotalCount(searchMap);
		
		log.info("[AdminOrderManageService] totalCount : {}", totalCount);
		
		/* 한 페이지에 보여줄 게시물의 수 */
		int limit = 10;
		/* 한 번에 보여질 페이징 버튼의 수 */
		int buttonAmount = 5;
		
		
		/* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
		SelectCriteria SelectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit,   buttonAmount, searchMap);
		log.info("[AdminOrderManageService] selectCriteria : {}", SelectCriteria);

		/* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */

		List<AdminOrderManageDTO> orderManage1 = adminOrderManageMapper.selectOrderManageList(SelectCriteria);
		log.info("[AdminOrderManageService] orderManage : {}", orderManage1);
		
		Map<String, Object> orderManage1AndPaging = new HashMap<>();
		orderManage1AndPaging.put("paging", SelectCriteria);
		orderManage1AndPaging.put("orderManage1", orderManage1);
		
		return orderManage1AndPaging;
		
		/*
		 * // 주문 목록을 가져오는 메서드 public List<AdminOrderManageDTO> getOrderList() { //매퍼
		 * 클래스의 getOrderList() 메서드를 호출하여 주문 목록 가져옴 return
		 * adminOrderManageMapper.getOrderList(); }
		 * 
		 * // 주문 상세 정보를 가져오는 메서드 public AdminOrderManageDTO getOrderDetail(Long orderId)
		 * { // 매퍼 클래스의 getOrderDetail() 메서드를 호출하여 주문 상세 정보를 가져옴 return
		 * adminOrderManageMapper.getOrderDetail(orderId);
		 */
    }


	public AdminOrderManageDTO selectOrderDetail(String orderNo) {
		

		
		/* 2. 게시글 상세 내용 조회 후 리턴 */
		return adminOrderManageMapper.selectOrderDetail(orderNo);

		
		
		
		
		
		
		
		
	}

}
