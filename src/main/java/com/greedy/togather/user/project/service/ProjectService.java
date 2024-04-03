package com.greedy.togather.user.project.service;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.togather.user.project.dao.ProjectMapper;
import com.greedy.togather.user.project.dto.FileDTO;
import com.greedy.togather.user.project.dto.LikeDTO;
import com.greedy.togather.user.project.dto.MakerDTO;
import com.greedy.togather.user.project.dto.ProjectDTO;
import com.greedy.togather.user.project.dto.ReplyDTO;
import com.greedy.togather.user.project.dto.RewardDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ProjectService {
	
	private final ProjectMapper projectMapper;
	
	public ProjectService(ProjectMapper projectMapper) {
		this.projectMapper = projectMapper;
	}
	
	/* 검색된 프로젝트 조회 */
	public Map<String, Object> selectSearchedProjectList(String word) {
		
		List<ProjectDTO> searchedProjectList = projectMapper.selectSearchedProjectList(word);
		log.info("[ProjectService] searchedProjectList : {}",  searchedProjectList);
		
		Map<String, Object> allProjectList = new HashMap<>();
		allProjectList.put("searchedProjectList", searchedProjectList);
		
		return allProjectList;
	}
	
	/* 프로젝트 리스트 조회 */
	public Map<String, Object> selectProjectList(String categoryNo) {
		
		List<ProjectDTO> projectList = projectMapper.selectProjectList(categoryNo);
		log.info("[ProjectService] projectList : {}",  projectList);
		
		Map<String, Object> allProjectList = new HashMap<>();
		allProjectList.put("projectList", projectList);
		
		return allProjectList;
	}
	
	/* 프로젝트 상세 페이지 조회 */
	public Map<String, Object> selectProjectDetail(String projNo, LikeDTO likeProject) {
		
		/* 프로젝트 상세 내용 */
		ProjectDTO projectDetail = projectMapper.selectProjectDetail(projNo);
		log.info("[ProjectService] projectDetail : {}", projectDetail);
		
		/* 리워드 조회 */
		List<RewardDTO> rewardList = projectMapper.selectRewardList(projNo);
		log.info("[ProjectService] rewardList : {}", rewardList);
		
		/* 총 기부금 & 댓글 개수 조회 */
		ReplyDTO donationAndReplyCount = projectMapper.selectDonationAndReplyCount(projNo);
		log.info("[ProjectService] donationAndReplyCount : {}", donationAndReplyCount);
		
		/* 현재 로그인한 유저가 해당 프로젝트를 좋아요 했는지 조회 */
		log.info("[ProjectService] likeProject : {}", likeProject);
		int loadIsLiked = projectMapper.loadIsLiked(likeProject);
		log.info("[ProjectService] loadIsLiked : {}", loadIsLiked);
		
		Map<String, Object> allProjectDetails = new HashMap<>();
		allProjectDetails.put("projectDetail", projectDetail);
		allProjectDetails.put("rewardList", rewardList);
		allProjectDetails.put("donationAndReplyCount", donationAndReplyCount);
		allProjectDetails.put("loadIsLiked", loadIsLiked);
		
		return allProjectDetails;
	}
	
	/* 프로젝트 상세 페이지 중, 댓글 */
	/* 조회 */
	public List<ReplyDTO> selectReplyList(ReplyDTO reply) {
		
		return projectMapper.selectReplyList(reply);
	}
	
	/* 등록 */
	public void insertReply(ReplyDTO reply) {
		
		projectMapper.insertReply(reply);
	}

	/* 프로젝트 신청 페이지 */
	public void createProject(ProjectDTO project, MakerDTO maker) {
		
		/* 각 테이블에 데이터 저장 */
		projectMapper.insertProjectInfo(project);
		
		projectMapper.insertMakerInfo(maker);		
		
		for(RewardDTO reward : project.getRewardList()) {
			projectMapper.insertRewardInfo(reward);
		}
		
		projectMapper.insertMakerProfile(project.getProcessedMakerProfile());
		
		projectMapper.insertMainImage(project.getProcessedMainImage());
		
		for(FileDTO file : project.getProcessedSubImageList()) {
			projectMapper.insertSubImage(file);
		}

		projectMapper.insertSettleDoc(project.getProcessedSettleDoc()); 
		
		projectMapper.insertAccountDoc(project.getProcessedAccountDoc());
		
		projectMapper.insertEtcDoc(project.getProcessedEtcDoc());
		
	}
	
	/* 현재 사용자가 해당 프로젝트를 좋아요 했는지 확인 후 좋아요 추가 or 취소 */
	public int isLikedByUser(LikeDTO likeProject) {
		
		int count = projectMapper.isLikedByUser(likeProject);
		
		if(count > 0) { 
			/* 좋아요 취소 */
			projectMapper.deleteProjectLike(likeProject);
		} else {
			/* 좋아요 추가 */
			projectMapper.insertProjectLike(likeProject);
		}
		
	    return count;
	}

	/* 프로젝트 후기 등록*/
	public void updateReview(ProjectDTO project) {
		
		projectMapper.updateReview(project);
		
	}

}
