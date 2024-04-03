package com.greedy.togather.user.user.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.greedy.togather.common.paging.SelectCriteria;
import com.greedy.togather.user.project.dto.LikeDTO;
import com.greedy.togather.user.project.dto.ProjectDTO;
import com.greedy.togather.user.user.model.dto.AuthDTO;
import com.greedy.togather.user.user.model.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	UserDTO findByUserId(String userId); 

	String selectUserById(String userId);

	int insertUser(UserDTO user);

	int insertUserRole();

	int updateUser(UserDTO user);

	int deleteUser(UserDTO user);

	int insertAuth(AuthDTO auth);

	int updatePwd(UserDTO updatePwd);

	String findLoginId(UserDTO user);

	UserDTO UserByEmail(String email);

	int updatePT(UserDTO tempUser);

	void registThumb(UserDTO user);

	void deleteThumb(UserDTO user);

	int selectTotalCount(Map<String, String> searchMap);

	List<ProjectDTO> selectLikeProject(String userNo, SelectCriteria selectCriteria);

	UserDTO selectUserProfile(String email);

	


	













	
}


