package com.greedy.togather.user.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.togather.user.main.dao.MainMapper;
import com.greedy.togather.user.project.dto.ProjectDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MainService {

	private final MainMapper mainMapper;
	
	public MainService(MainMapper mainMapper) {
		this.mainMapper = mainMapper;
	}
	
	public Map<String, Object> selectProjectsInMainPage() {
		
		List<ProjectDTO> mainProjects = mainMapper.selectMainProject();
		List<ProjectDTO> todayProjects = mainMapper.selectTodayProject();
		List<ProjectDTO> popularProjects = mainMapper.selectPopularProject();
		
		log.info("[MainService] mainProjects : {}", mainProjects);
		log.info("[MainService] todayProjects : {}", todayProjects);
		log.info("[MainService] popularProjects : {}", popularProjects);
		
		Map<String, Object> projectsInMainPage = new HashMap<>();
		projectsInMainPage.put("mainProjects", mainProjects);
		projectsInMainPage.put("todayProjects", todayProjects);
		projectsInMainPage.put("popularProjects", popularProjects);
		
		return projectsInMainPage;
	}

}
