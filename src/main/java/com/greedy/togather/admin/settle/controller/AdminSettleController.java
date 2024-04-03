package com.greedy.togather.admin.settle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greedy.togather.admin.settle.model.dto.AdminFundingDTO;
import com.greedy.togather.admin.settle.model.dto.AdminProjectDTO;
import com.greedy.togather.admin.settle.model.dto.AdminSettleDTO;
import com.greedy.togather.admin.settle.model.service.AdminSettleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/settle")
public class AdminSettleController {
	
	private AdminSettleService adminSettleService;
	
	public AdminSettleController(AdminSettleService adminSettleService) {
		 this.adminSettleService = adminSettleService;
	}
	
	/* 리스트 페이지 조회 기능(페이징, 검색기능) */
	@GetMapping("/list")
	public String selectSettleList(@RequestParam(defaultValue="1") int page, 
			@RequestParam(required=false) String searchCondition, 
			@RequestParam(required=false) String searchValue,
			Model model) {
		
		log.info("[AdminSettleController] page : {}", page);
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		log.info("[AdminSettleController] searchMap : {}", searchMap);
		
		Map<String, Object> settleListAndPaging = adminSettleService.selectSettleList(searchMap, page);
		model.addAttribute("paging", settleListAndPaging.get("paging"));
		model.addAttribute("settleList", settleListAndPaging.get("settleList"));
		
		return "admin/settle/settleList";
	}
	
	/* 삭제 기능 (체크박스) */
	@ResponseBody 
	@PostMapping("/delete")
	public String deletesettleChecked(@RequestParam(value="checkBox[]") List<String> checkList, AdminSettleDTO settle) {
		
		/* 체크된 리스트가 가지고 있는 settleNo를 settleDTO에 set한 후에 service 레이어에 보낸다 */ 
		
		log.info("[AdminSettleController] checkList {}", checkList);
		
		for(String checkInfo : checkList) {
			settle.setSettleNo(checkInfo);
			log.info("[AdminSettleController] settle {}", settle);
			
			adminSettleService.deleteSettleChecked(settle);
		}

		return "success"; //응답 바디에 담은 값 success라는 반환값을  data 로 전달 ;
	}
	
	/* 해당 프로젝트 정산 상세 페이지 조회 기능 */
	@GetMapping("/detail/{settleNo}")
	public String selectSettleDetail(@PathVariable("settleNo") String settleNo, Model model) {
		
		log.info("[AdminSettleController] settleNo : {}", settleNo);
		
		Map<String, Object> settleInfo = adminSettleService.selectSettleDetail(settleNo);
		
		model.addAttribute("projInfo", settleInfo.get("projInfo"));
		model.addAttribute("fundingInfo", settleInfo.get("fundingInfo"));
		model.addAttribute("totalPay", settleInfo.get("totalpay"));
		model.addAttribute("totalRefund", settleInfo.get("totalRefund"));
		model.addAttribute("totalFunding", settleInfo.get("totalFunding"));
		model.addAttribute("mnCharge", settleInfo.get("mnCharge"));
		model.addAttribute("payCharge", settleInfo.get("payCharge"));
		
		
		return "admin/settle/settleDetail";
	}
	
	@ResponseBody
	@PostMapping("/update")
	public String doSettle(@RequestParam String projNo) {
		
		log.info("[Controller_doSettle] projNo : {}", projNo);

		adminSettleService.doSettle(projNo);
		
		return "success";
	}
	
	/* 정산 테이블로 마감 프로젝트 가져오는 스케쥴링 */
	//@Scheduled(cron="0 0 0 * * *") // 매일 자정마다 동작
	//@Scheduled(cron="0 * * * * *") // 1분마다 동작
	public void insertEndProject() {
		
		List<AdminProjectDTO> projList = adminSettleService.selectEndProject();
		
		log.info("[AdminSettleController] projList : {}", projList);
		
		for(AdminProjectDTO project : projList) {			
			String projNo = project.getProjNo();			
			adminSettleService.insertTblSettle(projNo);
		}
	}
	

}
