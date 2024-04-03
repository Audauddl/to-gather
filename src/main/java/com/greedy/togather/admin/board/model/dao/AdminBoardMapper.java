package com.greedy.togather.admin.board.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.greedy.togather.admin.board.model.dto.AdminBoardDTO;
import com.greedy.togather.admin.project.common.SelectCriteria;


@Mapper
public interface AdminBoardMapper {

	int selectTotalCount(Map<String, String> searchMap);

	List<AdminBoardDTO> selectBoardList(SelectCriteria selectCriteria);


//	void deleteBoardChecked(AdminBoardDTO board);

	

}








