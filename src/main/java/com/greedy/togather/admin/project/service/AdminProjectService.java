package com.greedy.togather.admin.project.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.togather.admin.mainBanner.dto.AdminBannerDTO;
import com.greedy.togather.admin.project.common.Pagenation;
import com.greedy.togather.admin.project.common.SelectCriteria;
import com.greedy.togather.admin.project.dao.AdminProjectMapper;
import com.greedy.togather.admin.project.dto.AdminFileDTO;
import com.greedy.togather.admin.project.dto.AdminProjectDTO;
import com.greedy.togather.admin.project.dto.AdminProjectPageDTO;
import com.greedy.togather.admin.project.dto.AdminRewardDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AdminProjectService{

	private final AdminProjectMapper adminProjectMapper;
	
	
	public AdminProjectService(AdminProjectMapper adminProjectMapper) {
		
		this.adminProjectMapper = adminProjectMapper;

	}

	public Map<String, Object> seletProjectList(Map<String, String> searchMap, int page) {
		
		/* 전체 게시글 수 확인 (검색어가 있는 경우를 포함*) => 페이징처리 계산을 위해서 */
		int totalCount = adminProjectMapper.selectTotalCount(searchMap);
		log.info("[AdminProjectService] totalCount : {}", totalCount);
		
		int limit = 10;
		int buttonAmount = 5;
		
		/* 페이징 처리와 연관된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
		log.info("[AdminProjectService] selectCriteria : {}", selectCriteria);
		
		/* 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
		List<AdminProjectDTO> adminProjectList = adminProjectMapper.selectAdminProjectList(selectCriteria);
		log.info("[AdminProjectService] adminProjectList : {}", adminProjectList);
		
		Map<String, Object> boardAndPaging = new HashMap<>();
		boardAndPaging.put("paging", selectCriteria);
		boardAndPaging.put("adminProjectList", adminProjectList);
		
		return boardAndPaging;
	}

	public Map<String, Object> readProjectPage(String projNo) {
		
		AdminProjectPageDTO adminProjectPage = adminProjectMapper.readProjectPage(projNo);
		log.info("[AdminProjectService] adminprojectpage : {}", adminProjectPage);
		                                                          
		List<AdminRewardDTO> adminRewardList = adminProjectMapper.readProjectPageReward(projNo);
		log.info("[AdminProjectService] adminprojectpage : {}", adminRewardList);
		
		List<AdminFileDTO> adminFileList = adminProjectMapper.adminReadFile(projNo);
		log.info("[AdminProjectService] adminReadFile : {}", adminFileList);
		
		Map<String, Object> adminProjectAll = new HashMap<>();
		adminProjectAll.put("adminProjectPage", adminProjectPage);
		adminProjectAll.put("adminRewardList", adminRewardList);
		adminProjectAll.put("adminFileList", adminFileList);

		log.info("[AdminProjectService] adminProjectAll : {}", adminProjectAll);
		
		
		return adminProjectAll;
	}
	
	
	
	

	public void updateConfirm(String projNo) {
		
		adminProjectMapper.updateConfirm(projNo);
		
	}

	public void updateReject(String projNo) {
		adminProjectMapper.updateReject(projNo);
		
	}

	public void updateStop(String projNo) {
		adminProjectMapper.updateStop(projNo);
		
	}
	
	public void adminMainBanner(AdminBannerDTO banner) {
		
		adminProjectMapper.adminMainBanner(banner);
			
	}
	
	public void adminMainBannerUpdate(AdminBannerDTO bannerUpdate) {
		
		adminProjectMapper.adminMainBannerUpdate(bannerUpdate);
		
	}

	public void adminTodayProject(AdminBannerDTO today) {
		
		adminProjectMapper.adminTodayProject(today);
	}


	public void adminTodayProjectUpdate(AdminBannerDTO todayUpdate) {
		
		adminProjectMapper.adminTodayProjectUpdate(todayUpdate);
	}





}
