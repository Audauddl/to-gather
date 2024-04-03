package com.greedy.togather.admin.mainBanner.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.greedy.togather.Chap99ToGatherApplication;
import com.greedy.togather.admin.mainBanner.dto.AdminBannerDTO;
import com.greedy.togather.admin.mainBanner.service.AdminBannerService;
import com.greedy.togather.admin.mainBanner.common.Pagenation;
import com.greedy.togather.admin.mainBanner.common.SelectCriteria;

@SpringBootTest
@ContextConfiguration(classes = {Chap99ToGatherApplication.class})
public class AdminBannerMapperTests {
	
	@Autowired
	private AdminBannerMapper adminBannerMapper;
	
	@Test
	@Disabled
	public void 매퍼_인터페이스_의존성_주입_테스트() {
		
		assertNotNull(adminBannerMapper);
	}
	
	@Test

	public void 배너_조회용_매퍼_테스트() {
		
		//given
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", "projNo");
		searchMap.put("searchValue", "PJ000000090");

		//when
		int totalCount = adminBannerMapper.selectTotalCount(searchMap);
		
		
		//then
		assertNotNull(totalCount);
	}
	
	@Test
	@Disabled
	public void 배너_리스트_매퍼_테스트() {
		
		//given
		int page = 1;
		int totalCount = 10;
		int limit = 3;
		int buttonAmount = 5;
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", "projNo");
		searchMap.put("searchValue", "PJ000000090");
		
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit,
				buttonAmount, searchMap);
		
		//when
		List<AdminBannerDTO> selectBannerList = adminBannerMapper.selectBannerList(selectCriteria);
		
		
		//then
		assertNotNull(selectBannerList);
		
		
	}
	


}
