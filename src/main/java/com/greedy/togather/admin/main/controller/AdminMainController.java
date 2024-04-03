package com.greedy.togather.admin.main.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.togather.user.user.model.dto.UserDTO;


@Controller
@RequestMapping("/admin")
public class AdminMainController {
	
	@GetMapping(value = "")
	public String main(@AuthenticationPrincipal UserDTO user, Model model) {
		

		return "admin/main/main";
	}

	
}
