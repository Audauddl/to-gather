package com.greedy.togather.admin.board.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.togather.admin.board.model.dao.AdminBoardMapper;
import com.greedy.togather.admin.board.model.dto.AdminBoardDTO;
import com.greedy.togather.admin.project.common.Pagenation;
import com.greedy.togather.admin.project.common.SelectCriteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AdminBoardService {
	
	private final AdminBoardMapper adminBoardMapper;
	
	public AdminBoardService (AdminBoardMapper adminBoardMapper) {
		this.adminBoardMapper = adminBoardMapper;
	}

	public Map<String, Object> selectViewBoardList(Map<String, String> searchMap, int page) {
		
		/* 1. 전체 게시글 수 확인 (검색어가 없는 경우, 있는 경우 모두 포함) => 페이징 처리 계산을 위해서 */
		int totalCount = adminBoardMapper.selectTotalCount(searchMap);
		log.info("[AdminBoardService] totalCount : {}", totalCount);
		
		/* 한 페이지에 보여줄 게시물의 수 */
		int limit = 10;
		/* 한 번에 보여질 페이징 버튼의 수 */
		int buttonAmount = 5;
		
		/* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
		SelectCriteria SelectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit,   buttonAmount, searchMap);
		log.info("[AdminBoardService] selectCriteria : {}", SelectCriteria);
		
		/* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
		List<AdminBoardDTO> viewBoardList = adminBoardMapper.selectBoardList(SelectCriteria);
		log.info("[AdminBoardService] boardList : {}", viewBoardList);
		
		Map<String, Object> viewBoardListAndPaging = new HashMap<>();
		viewBoardListAndPaging.put("paging", SelectCriteria);
		viewBoardListAndPaging.put("viewBoardList", viewBoardList);
		
		return viewBoardListAndPaging;
	}

		

//	public void deleteBoardChecked(AdminBoardDTO board) {
	//	adminBoardMapper.deleteBoardChecked(board);

	}

	
	
