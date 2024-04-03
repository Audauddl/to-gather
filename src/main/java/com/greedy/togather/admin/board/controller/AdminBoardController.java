package com.greedy.togather.admin.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greedy.togather.admin.board.model.dto.AdminBoardDTO;
import com.greedy.togather.admin.board.service.AdminBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/board")
public class AdminBoardController {

	private final AdminBoardService adminBoardService;

	public AdminBoardController(AdminBoardService adminBoardService) {
		this.adminBoardService = adminBoardService;
	}

	@GetMapping("/list")
	public String selectViewBoardList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false) String searchCondition, @RequestParam(required = false) String searchValue,
			Model model) {
		log.info("[AdminBoardController] page : {}", page);

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);

		log.info("[AdminBoardController] searchMap : {}", searchMap);

		Map<String, Object> viewBoardListAndPaging = adminBoardService.selectViewBoardList(searchMap, page);
		model.addAttribute("paging", viewBoardListAndPaging.get("paging"));
		model.addAttribute("viewBoardList", viewBoardListAndPaging.get("viewBoardList"));
		
		return "admin/board/viewBoardList" ;
		
	}
	
	
	
	
	@GetMapping("/listdatail")
	public String selectBoardDetail() {
		
		return "admin/board/viewPost";
	}

	/*@GetMapping("/Write")
	public String goWrite() {
		
		return "admin/board/boardWrite";
	}
	
	 게시글 등록 컨트롤러 핸들러 메소드  
	@PostMapping("/Write")
	public String writeBoard(AdminBoardDTO board, @AuthenticationPrincipal MemberDTO member,
			RedirectAttributes rttr) {
		
		board.setWriter(member);
		log.info("[BoardController] board : {}", board);
		
		boardService.registBoard(board);
		
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("board.regist"));
		
		return "redirect:/board/list";*/
	}
	
	
	
	
	
	
	/* // /* 삭제 기능 (체크박스) 
	@ResponseBody
	@PostMapping("/delete")
	public String deleteBoardChecked(@RequestParam(value="checkBox[]") List<String> checkList, AdminBoardDTO Board) {
		
		 체크된 리스트가 가지고 있는 BoardNo를 BoardDTO에 set한 후에 service 레이어에 보낸다  
		
		log.info("[AdminBoardController] checkList {}", checkList );
		
		for(String checkInfo : checkList) {
			Board.setBoardNo(checkInfo);
			log.info("[AdminBoardController] Board {}", Board);	
			adminBoardService.deleteBoardChecked(Board);
		}

		return "redirect:/admin/Board/viewBoardList";
	}*/


