package com.greedy.togather.admin.orderManage.model.dao;

	import org.apache.ibatis.annotations.Mapper;

import com.greedy.togather.admin.orderManage.model.dto.AdminOrderManageDTO;
import com.greedy.togather.common.paging.SelectCriteria;


import java.util.List;
import java.util.Map;

// 주문 관리 데이터를 처리하는 매퍼 인터페이스
 @Mapper
public interface AdminOrderManageMapper {

	 
		int selectTotalCount(Map<String, String> searchMap);

		 List<AdminOrderManageDTO> selectOrderManageList(SelectCriteria selectCriteria);


		 
		 
		AdminOrderManageDTO selectOrderDetail(String orderNo);

		
		 
		}		
		
		
		
		/*
		 * // 주문 목록을 가져오는 메서드 List<AdminOrderManageDTO> getOrderList();
		 * 
		 * // 주문 상세 정보를 가져오는 메서드 AdminOrderManageDTO getOrderDetail(Long orderId);
		 */


