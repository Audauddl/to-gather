package com.greedy.togather.admin.orderManage.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.togather.admin.orderManage.model.dto.AdminOrderManageDTO;
import com.greedy.togather.admin.orderManage.service.AdminOrderManageService;
import com.greedy.togather.admin.user.model.dto.AdminFundingDTO;

import lombok.extern.slf4j.Slf4j;

// 주문 관리 페이지 컨트롤러 클래스
@Slf4j
@Controller
@RequestMapping("/admin/orderManage")
public class AdminOrderManageController {

	// 서비스 클래스를 주입
	@Autowired
    private final AdminOrderManageService adminOrderManageService;
	
	public AdminOrderManageController(AdminOrderManageService adminOrderManageService) {
		this.adminOrderManageService = adminOrderManageService;
	}
	
    // 주문 정보를 불러오는 메소드
    @GetMapping("/orderList")
    public String selectOrderList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false) String searchCondition, @RequestParam(required = false) String searchValue,
			Model model) {
        // 주문 정보 목록을 반환
    	
    	Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
    	
    	
    	Map<String, Object> orderManage1AndPaging = adminOrderManageService.selectOrderManage1(searchMap, page);
		model.addAttribute("paging", orderManage1AndPaging.get("paging"));
		model.addAttribute("orderManage1", orderManage1AndPaging.get("orderManage1"));
    	
    	
        return "admin/orderManage/orderManage1";
        
        
        
        
        
    }
    
    //세부 정보 불러오는 기능
        @GetMapping("/detail/{orderNo}")
    	public String selectOrderDetail(@PathVariable("orderNo") String orderNo, Model model) {
    		 
        	
        	
        	
        	AdminOrderManageDTO orderDetail = adminOrderManageService.selectOrderDetail(orderNo);
    		log.info("[AdminOrderManageController] orderDetail : {}", orderDetail);
    		
    		model.addAttribute("orderManage2", orderDetail);
    		
    		return "admin/orderManage/orderManage2";
        }
        
        
        
        

		/*
		 * // 상세 정보를 불러오는 메소드
		 * 
		 * @GetMapping("/detail") public String selectOrderManageDetail(@RequestParam
		 * Long no, Model model) {
		 * 
		 * AdminOrderManageDTO boardDetail = boardService.selectBoardDetail(no);
		 * log.info("[BoardController] boardDetail : {}", boardDetail);
		 * 
		 * model.addAttribute("board", boardDetail);
		 * 
		 * return "board/boardDetail";
		 */
        
        
        
        

	// 주문 상세 정보를 가져오는 API
	/*
	 * @GetMapping("/detail/{orderId}") public AdminOrderManageDTO
	 * getOrderDetail(@PathVariable Long orderId) {
	 */
	// 서비스 클래스의 getOrderDetail() 메서드를 호출하여 주문 상세 정보를 가져옴
	/*
	 * return adminOrderManageService.getOrderDetail(orderId);
	 */
    }
    

