package com.greedy.togather.admin.mainBanner.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.greedy.togather.admin.mainBanner.common.SelectCriteria;
import com.greedy.togather.admin.mainBanner.dto.AdminBannerDTO;
import com.greedy.togather.admin.mainBanner.dto.AdminBannerFileDTO;

@Mapper
public interface AdminBannerMapper {

	int selectTotalCount(Map<String, String> searchMap);

	List<AdminBannerDTO> selectBannerList(SelectCriteria selectCriteria);

	AdminBannerDTO bannerReadPage(String projNo);

	AdminBannerFileDTO adminBannerReadFile(String projNo);


}
