package com.greedy.togather.admin.user.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.togather.admin.user.model.dao.AdminUserMapper;
import com.greedy.togather.admin.user.model.dto.AdminFundingDTO;
import com.greedy.togather.admin.user.model.dto.AdminUserDTO;
import com.greedy.togather.common.paging.Pagenation;
import com.greedy.togather.common.paging.SelectCriteria;
import com.greedy.togather.user.user.model.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
public class AdminUserService {
	
	private final AdminUserMapper adminUserMapper;
	
	public AdminUserService(AdminUserMapper adminUserMapper) {
		this.adminUserMapper = adminUserMapper;
	}
	
	public Map<String, Object> selectUserList(Map<String, String> searchMap, int page) {
			
		int totalCount = adminUserMapper.selectTotalCount(searchMap);
		log.info("[AdminUserService] totalCount : {}", totalCount);
		
		int limit = 10;
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
		log.info("[AdminUserService] selectCriteria : {}", selectCriteria);
		
		List<AdminUserDTO> userList = adminUserMapper.selectUserList(selectCriteria);
		log.info("[AdminUserService] userList : {}", userList);
		
		Map<String, Object> userListAndPaging = new HashMap<>();
		userListAndPaging.put("paging", selectCriteria);
		userListAndPaging.put("userList", userList);
		
		return userListAndPaging;
	}

	public void deleteUserChecked(AdminUserDTO user) {

		adminUserMapper.deleteUserChecked(user);
	}

	public AdminUserDTO selectUserDetail(String userNo) {
		
		return adminUserMapper.selectUserDetail(userNo);
	}

	public List<AdminFundingDTO> selectFundingDetail(String userNo) {
		return adminUserMapper.selectFundingDetail(userNo);
	}

	public void modifyUserInfo(UserDTO user) {
			adminUserMapper.modifyUserInfo(user);
	}


	






}
