package com.greedy.togather.user.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.greedy.togather.user.main.service.MainService;
import com.greedy.togather.user.project.dto.ProjectDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	private final MainService mainService;
	private final MessageSourceAccessor messageSourceAccessor;
	
	public MainController(MainService mainService, MessageSourceAccessor messageSourceAccessor) {
		this.mainService = mainService;
		this.messageSourceAccessor = messageSourceAccessor;
	}
	
	@GetMapping(value = {"/", "/main"})
	public String main(Model model) {
		
		Map<String, Object> projectsInMainPage = mainService.selectProjectsInMainPage();
		model.addAttribute("mainProjects", projectsInMainPage.get("mainProjects"));
		model.addAttribute("todayProjects", projectsInMainPage.get("todayProjects"));
		model.addAttribute("popularProjects", projectsInMainPage.get("popularProjects"));
		
		return "user/main/main";
	}
	
	@PostMapping(value="/")
	public String redirectMain() {
		return "redirect:/";
	}
	
	@GetMapping("/user/chat")
	public String goChat() {
		
		return "common/header/userChat";
	}
	
	
	
}
