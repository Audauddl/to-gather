package com.greedy.togather.user.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.greedy.togather.Chap99ToGatherApplication;
import com.greedy.togather.user.project.dto.ReplyDTO;

@SpringBootTest
@ContextConfiguration(classes = {Chap99ToGatherApplication.class})
public class ProjectServiceTests {
	
	@Autowired
	private ProjectService projectService;
	
	@Test
	@DisplayName("프로젝트 리스트 조회용 서비스 테스트")
	public void selectProjectListTest() {
		
		// given
		int page = 1;
		String categoryNo = "CA002";
		
		// when
		Map<String, Object> projectList = projectService.selectProjectList(categoryNo);
		
		// then
		assertNotNull(projectList);
		System.out.println(projectList);
	}
	
	/*
	 * @Test
	 * 
	 * @DisplayName("프로젝트 상세 페이지 조회용 서비스 테스트") public void selectProjectDetailTest()
	 * {
	 */
		// given
		
		// when
		/*
		 * Map<String, Object> projectList =
		 * projectService.selectProjectDetail("PJ000000001");
		 */
				
		/*
		 * // then assertNotNull(projectList); System.out.println(projectList);
		 * 
		 * }
		 */
	
	@Test
	@DisplayName("댓글 조회용 서비스 테스트")
	public void selectReplyListTest() {
		
		// given
		ReplyDTO reply = new ReplyDTO();
		reply.setProjNo("PJ000000001");
				
		// when
		List<ReplyDTO> replyList = projectService.selectReplyList(reply);
		
								
		// then
		assertNotNull(replyList);
		System.out.println(replyList);
	}
	
	@Test
	@DisplayName("댓글 등록용 서비스 성공 테스트")
	public void SuccessInsertReply() {
		// given
		ReplyDTO reply = new ReplyDTO();
		reply.setUserNo("US000000004");
		reply.setProjNo("PJ000000002");
		reply.setReplyBody("우와 엄청나네요!! 지인들에게 공유하겠습니다 응원해요!!");
		reply.setDonation(0);
		
		// when
//		int result = projectService.insertReply(reply);
		
		// then
//		assertEquals(1, result);
		
	}
	
	@Test
	@DisplayName("댓글 등록용 서비스 실패 테스트")
	public void FailInsertReply() {
		// given
		ReplyDTO reply = new ReplyDTO();
		reply.setUserNo("US000000004"); 
		reply.setProjNo(null); /* 회원번호에 null 입력하여 예외 발생 */
		reply.setReplyBody("이 댓글은 등록이 되면 안됩니다 ^^");
		reply.setDonation(0);
		
		// when
		
		// then
		assertThrows(Exception.class, () -> projectService.insertReply(reply));
		
	}
	

}
