package com.greedy.togather.admin.mainBanner.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.togather.admin.mainBanner.dto.AdminBannerDTO;
import com.greedy.togather.admin.mainBanner.dto.AdminBannerFileDTO;
import com.greedy.togather.admin.mainBanner.service.AdminBannerService;
import com.greedy.togather.admin.project.dao.StatusDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/mainbanner")
public class AdminBannerController {
	
	private final AdminBannerService adminBannerService;
	
	public AdminBannerController(AdminBannerService adminBannerService) {
		
		this.adminBannerService = adminBannerService;
	}

	@GetMapping("list")
	public String bannerList(@RequestParam(defaultValue="1") int page, 
			@RequestParam(required=false) String searchCondition, 
			@RequestParam(required=false) String searchValue,
			Model model) {
		
		log.info("[AdminBannerController] page : {}", page);
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		log.info("[AdminBannerController] searchMap : {}", searchMap);
		
		Map<String, Object> boardListAndPaging = adminBannerService.selectBannerList(searchMap, page);
		model.addAttribute("paging", boardListAndPaging.get("paging"));
		model.addAttribute("bannerList", boardListAndPaging.get("bannerList"));
		
		
		return "admin/mainBanner/mainBannerList";
		
	}
	
	@GetMapping("/home")
	public String adminMainbannerHome(Model model) {
		
		Map<String, StatusDTO> mainStatusMap = new HashMap<>();
		mainStatusMap.put("statStart", new StatusDTO("mpStatus", "게시"));
		mainStatusMap.put("statEnd", new StatusDTO("mpStatus", "마감"));
		mainStatusMap.put("statMain", new StatusDTO("mpCategory", "메인배너"));
		mainStatusMap.put("statToday", new StatusDTO("mpCategory", "오늘의 프로젝트"));

		model.addAttribute("mainStatusMap", mainStatusMap);
		
		log.info("[AdminBannerController]  : {}", mainStatusMap);
		
		
		return "admin/mainBanner/mainBannerHome";
	}
	
	@GetMapping("/page")
	public String adminMainbannerPage(@RequestParam(value="projNo", required=false) String projNo, Model model) {
		
		AdminBannerDTO adminBannerPage = adminBannerService.bannerReadPage(projNo);
		
		AdminBannerFileDTO adminBannerFileList = adminBannerService.AdminBannerFileDTO(projNo);
		
		model.addAttribute("Banner", adminBannerPage);
		model.addAttribute("bannerFile", adminBannerFileList);
		log.info("[AdminBannerController] adminBannerFileList : {}", adminBannerFileList);
		
				
		return "admin/mainBanner/mainBannerReg";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
