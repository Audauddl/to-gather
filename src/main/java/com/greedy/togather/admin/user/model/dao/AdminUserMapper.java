package com.greedy.togather.admin.user.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.greedy.togather.admin.user.model.dto.AdminFundingDTO;
import com.greedy.togather.admin.user.model.dto.AdminUserDTO;
import com.greedy.togather.common.paging.SelectCriteria;
import com.greedy.togather.user.user.model.dto.UserDTO;

@Mapper
public interface AdminUserMapper {
	
		int selectTotalCount(Map<String, String> searchMap);

		List<AdminUserDTO> selectUserList(SelectCriteria selectCriteria);

		void deleteUserChecked(AdminUserDTO user);

		AdminUserDTO selectUserDetail(String userNo);

		List<AdminFundingDTO> selectFundingDetail(String userNo);

		void modifyUserInfo(UserDTO user);



	}


