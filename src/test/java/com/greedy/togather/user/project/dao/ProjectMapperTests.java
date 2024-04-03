package com.greedy.togather.user.project.dao;

import static org.junit.jupiter.api.Assertions.assertEquals; 
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.greedy.togather.Chap99ToGatherApplication;
import com.greedy.togather.user.project.dto.ProjectDTO;
import com.greedy.togather.user.project.dto.ReplyDTO;
import com.greedy.togather.user.project.dto.RewardDTO;

@SpringBootTest
@ContextConfiguration(classes = {Chap99ToGatherApplication.class})
public class ProjectMapperTests {
	
	@Autowired
	private ProjectMapper projectMapper;
	
	@Test
	@DisplayName("검색된 프로젝트 조회용 매퍼 테스트")
	public void selectSearchedProjectListTest() {
		// given
		
		// when
		List<ProjectDTO> projectList = projectMapper.selectProjectList("프로젝트");
				
		// then
		assertNotNull(projectList);
		System.out.println(projectList);
		
	}
	
	
	@Test
	@Disabled
	@DisplayName("프로젝트 리스트 조회용 매퍼 테스트")
	public void selectProjectListTest() {
		// given
		
		// when
		List<ProjectDTO> projectList = projectMapper.selectProjectList("CA003");
		
		// then
		assertNotNull(projectList);
		System.out.println(projectList);
	}
	/*
	 * @Test
	 * 
	 * @Disabled
	 * 
	 * @DisplayName("프로젝트 리스트 조회용 매퍼 테스트") public void selectProjectListTest() { //
	 * given MoreSelectCriteria critetia = new MoreSelectCriteria();
	 * critetia.setEndRow(3); critetia.setStartRow(1);
	 * 
	 * // when List<ProjectDTO> projectList =
	 * projectMapper.selectProjectList(critetia, "CA003");
	 * 
	 * // then assertNotNull(projectList); System.out.println(projectList); }
	 */
	
	@Test
	@DisplayName("프로젝트 상세 페이지 조회용 매퍼 테스트")
	public void selectProjectDetailTest() {
		// given
		
		// when
		ProjectDTO projectDetail = projectMapper.selectProjectDetail("PJ000000001"); 
		
		// then
		assertNotNull(projectDetail);
		System.out.println(projectDetail);
	}
	
	@Test
	@DisplayName("리워드 조회용 매퍼 테스트")
	public void selectRewardListTest() {
		// given
		
		// when
		List<RewardDTO> rewardList = projectMapper.selectRewardList("PJ000000001");
		
		// then
		assertNotNull(rewardList);
		System.out.println(rewardList);
	}
	
	@Test
	@Disabled
	@DisplayName("총 기부금 & 댓글 개수 조회용 매퍼 테스트")
	public void selectDonationAndReplyCountTest() {
		// given
		
		// when
		ReplyDTO reply = projectMapper.selectDonationAndReplyCount("PJ000000001");
		
		// then
		assertNotNull(reply);
		System.out.println(reply);
	}
	
	@Test
	@DisplayName("댓글 조회용 매퍼 테스트")
	public void selectReplyListTest() {
		// given
		ReplyDTO reply = new ReplyDTO();
		reply.setProjNo("PJ000000001");
		
		// when
		List<ReplyDTO> replyList = projectMapper.selectReplyList(reply);
		
		// then
		assertNotNull(replyList);
		System.out.println(replyList);
	}
	
	@Test
	@DisplayName("댓글 등록용 매퍼 테스트")
	public void insertReply() {
		// given
		ReplyDTO reply = new ReplyDTO();
		reply.setUserNo("US000000005");
		reply.setProjNo("PJ000000002");
		reply.setReplyBody("의미있는 프로젝트 응원합니다 :)");
		reply.setDonation(100);
		
		// when
//		int result = projectMapper.insertReply(reply);
		
		// then
//		assertEquals(1, result);
		
	}

}
