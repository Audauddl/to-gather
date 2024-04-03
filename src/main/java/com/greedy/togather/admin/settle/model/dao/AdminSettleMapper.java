package com.greedy.togather.admin.settle.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.greedy.togather.admin.settle.model.dto.AdminFundingDTO;
import com.greedy.togather.admin.settle.model.dto.AdminProjectDTO;
import com.greedy.togather.admin.settle.model.dto.AdminSettleDTO;

import com.greedy.togather.common.paging.SelectCriteria;

@Mapper
public interface AdminSettleMapper {

    int selectTotalCount(Map<String, String> searchMap);

	List<AdminSettleDTO> selectSettleList(SelectCriteria selectCriteria);

	void deleteSettleChecked(AdminSettleDTO settle);

	AdminSettleDTO selectProjInfo(String settleNo);

	List<AdminFundingDTO> selectFundingInfo(String projNo);

	void doSettle(String projNo);

	List<AdminProjectDTO> selectEndProject();

	void insertTblSettle(String projNo);

}