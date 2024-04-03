package com.greedy.togather.admin.mainBanner.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.togather.admin.mainBanner.common.Pagenation;
import com.greedy.togather.admin.mainBanner.common.SelectCriteria;
import com.greedy.togather.admin.mainBanner.dao.AdminBannerMapper;
import com.greedy.togather.admin.mainBanner.dto.AdminBannerDTO;
import com.greedy.togather.admin.mainBanner.dto.AdminBannerFileDTO;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AdminBannerService {
	
	private final AdminBannerMapper adminBannerMapper;
	
	public AdminBannerService(AdminBannerMapper adminBannerMapper) {
		
		this.adminBannerMapper = adminBannerMapper;

	}


	public Map<String, Object> selectBannerList(Map<String, String> searchMap, int page) {
		
		
		/*1. 전체 게시물 수 확인 */
		int totalCount = adminBannerMapper.selectTotalCount(searchMap);
		
		log.info("[AdminBannerService] : totalCount : {}", totalCount);
		
		/* 한 페이지에 보여줄 게시물의 수 */
		int limit = 10;
		/* 한 번에 보여질 페이징 버튼의 수 */
		int buttonAmount = 5;
		
		/* 페이지와 연관된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
		
		log.info("[AdminBannerService] : SelectCriteria : {}", selectCriteria);
		
		/* 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
		List<AdminBannerDTO> bannerList = adminBannerMapper.selectBannerList(selectCriteria);
		
		log.info("[AdminBannerService] : bannerList : {}", bannerList);
		
	
		Map<String, Object> boardListAndPaging = new HashMap<>();
		boardListAndPaging.put("paging", selectCriteria);
		boardListAndPaging.put("bannerList", bannerList);
		
		
		return boardListAndPaging;
	}


	public AdminBannerDTO bannerReadPage(String projNo) {

		AdminBannerDTO adminBannerPage = adminBannerMapper.bannerReadPage(projNo);
		log.info("[AdminBannerService] adminBannerPage : {}", adminBannerPage);
		
		return adminBannerPage;
		
	}

	
	public AdminBannerFileDTO AdminBannerFileDTO(String projNo) {
		
		AdminBannerFileDTO adminBannerFileList = adminBannerMapper.adminBannerReadFile(projNo);
		
		
		return adminBannerFileList;
	}




	
	
	
	
	
}
