package com.greedy.togather.admin.project.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.greedy.togather.admin.mainBanner.dto.AdminBannerDTO;
import com.greedy.togather.admin.project.common.SelectCriteria;
import com.greedy.togather.admin.project.dto.AdminFileDTO;
import com.greedy.togather.admin.project.dto.AdminProjectDTO;
import com.greedy.togather.admin.project.dto.AdminProjectPageDTO;
import com.greedy.togather.admin.project.dto.AdminRewardDTO;

@Mapper
public interface AdminProjectMapper {

	int selectTotalCount(Map<String, String> searchMap);

	List<AdminProjectDTO> selectAdminProjectList(SelectCriteria selectCriteria);

	AdminProjectPageDTO readProjectPage(String projNo);

	List<AdminRewardDTO> readProjectPageReward(String projNo);

	void updateConfirm(String projNo);

	void updateReject(String projNo);

	void updateStop(String projNo);

	List<AdminFileDTO> adminReadFile(String projNo);

	void adminMainBanner(AdminBannerDTO banner);

	void adminMainBannerUpdate(AdminBannerDTO bannerUpdate);

	void adminTodayProject(AdminBannerDTO today);

	void adminTodayProjectUpdate(AdminBannerDTO todayUpdate);

	
}
