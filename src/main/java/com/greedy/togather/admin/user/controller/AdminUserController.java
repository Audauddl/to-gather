package com.greedy.togather.admin.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greedy.togather.admin.user.model.dto.AdminFundingDTO;
import com.greedy.togather.admin.user.model.dto.AdminUserDTO;
import com.greedy.togather.admin.user.model.service.AdminUserService;
import com.greedy.togather.user.user.model.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
	
	private final AdminUserService adminUserService;
	public AdminUserController(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	
	/* 리스트 페이지 조회 기능(페이징, 검색기능) */
	@GetMapping("/list")
	public String selectUserList(@RequestParam(defaultValue="1") int page, 
			@RequestParam(required=false) String searchCondition, 
			@RequestParam(required=false) String searchValue,
			Model model) {
		
		log.info("[AdminUserController] page : {}", page);
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		log.info("[AdminUserController] searchMap : {}", searchMap);
		
		Map<String, Object> userListAndPaging = adminUserService.selectUserList(searchMap, page);
		model.addAttribute("paging", userListAndPaging.get("paging"));
		model.addAttribute("userList", userListAndPaging.get("userList"));
		
		return "admin/user/userList";
	}
	
	/* 삭제 기능 (체크박스) */
	@ResponseBody
	@PostMapping("/delete")
	public String deleteUserChecked(@RequestParam(value="checkBox[]") List<String> checkList, AdminUserDTO user) {
		
		/* 체크된 리스트가 가지고 있는 userNo를 UserDTO에 set한 후에 service 레이어에 보낸다 */ 
		
		log.info("[AdminUserController] checkList {}", checkList);
		
		for(String checkInfo : checkList) {
			user.setUserNo(checkInfo);
			log.info("[AdminUserController] user {}", user);
			
			adminUserService.deleteUserChecked(user);
		}

		return "redirect:/admin/user/list";
	}
	
	
	/* 해당 유저의 상세 조회 페이지 조회 기능 */
	@GetMapping("/detail/{userNo}")
	public String selectUserDetail(@PathVariable("userNo") String userNo, Model model) {
		
		log.info("[AdminUserController] userNo : {}", userNo);
		
		/* 유저 정보 조회*/
		AdminUserDTO userDetail = adminUserService.selectUserDetail(userNo);
		log.info("[AdminUserController] userDetail : {}", userDetail);
		
		/* 펀딩 내역 조회*/
		List<AdminFundingDTO> fundingDetail = adminUserService.selectFundingDetail(userNo);
		log.info("[AdminUserController] fundingDetail : {}", fundingDetail);
		
		/* 펀딩 합계 */
		int totalPay = 0;
		int totalRefund = 0;
		
		for(AdminFundingDTO funding : fundingDetail) {
			totalPay += funding.getPay().getPayPrice();
			totalRefund += funding.getPay().getRefund().getRefundPrice();
		}
		
		model.addAttribute("user", userDetail);
		model.addAttribute("fundingList", fundingDetail);
		model.addAttribute("totalPay", totalPay);
		model.addAttribute("totalRefund", totalRefund);
		
		return "admin/user/userDetail";
	}
	
	/* 해당 유저의 정보 수정 */
	@ResponseBody
	@PostMapping("/update")
	public String modifyUserInfo(@ModelAttribute UserDTO updateUser,
    		@RequestParam String address1, @RequestParam String address2,
    		@RequestParam String userNo) {
		
		
		
		String address = "$" + address1 + "$" + address2;
		
		updateUser.setAddress(address);
		updateUser.setUserNo(userNo);
		
		adminUserService.modifyUserInfo(updateUser);
		
		return "success";
	}

	
	

}