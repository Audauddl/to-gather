package com.greedy.togather.user.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.greedy.togather.user.project.dto.ProjectDTO;

@Mapper
public interface MainMapper {

	/* 메인 프로젝트 조회 */
	List<ProjectDTO> selectMainProject();

	/* 오늘의 프로젝트 조회 */
	List<ProjectDTO> selectTodayProject();

	/* 인기 프로젝트 조회 */
	List<ProjectDTO> selectPopularProject();

}
