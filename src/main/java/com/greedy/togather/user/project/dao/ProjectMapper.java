package com.greedy.togather.user.project.dao;

import java.util.List; 

import org.apache.ibatis.annotations.Mapper;

import com.greedy.togather.user.project.dto.FileDTO;
import com.greedy.togather.user.project.dto.LikeDTO;
import com.greedy.togather.user.project.dto.MakerDTO;
import com.greedy.togather.user.project.dto.ProjectDTO;
import com.greedy.togather.user.project.dto.ReplyDTO;
import com.greedy.togather.user.project.dto.RewardDTO;

import lombok.extern.slf4j.Slf4j;

@Mapper
public interface ProjectMapper {

	/* 검색된 프로젝트 조회 */
	List<ProjectDTO> selectSearchedProjectList(String word);
	
	/* 프로젝트 리스트 조회 */
	List<ProjectDTO> selectProjectList(String categoryNo);
	
	
	/* 프로젝트 상세 페이지 */
	/* 전체 화면 조회 */
	ProjectDTO selectProjectDetail(String projNo);
	
	/* 리워드 조회 */
	List<RewardDTO> selectRewardList(String projNo);
	
	/* 총 기부금 & 댓글 개수 조회 */
	ReplyDTO selectDonationAndReplyCount(String projNo);
	
	/* 댓글 조회 & 등록 */
	List<ReplyDTO> selectReplyList(ReplyDTO reply);
	void insertReply(ReplyDTO reply);
	
	
	/* 프로젝트 신청 */
	/* TBL_PROJECT에 정보 등록 */
	void insertProjectInfo(ProjectDTO project);
	
	/* TBL_MAKER에 메이커 정보 등록 */
	void insertMakerInfo(MakerDTO maker);
	
	/* TBL_REWARD에 정보 등록 */
	void insertRewardInfo(RewardDTO reward);
	
	/* TBL_FILE에 메이커 프로필 등록 */
	void insertMakerProfile(FileDTO makerProfile);
	
	/* TBL_FILE에 메인 이미지 등록 */
	void insertMainImage(FileDTO mainImage);
	
	/* TBL_FILE에 서브 이미지 등록 */
	void insertSubImage(FileDTO file);
	
	/* TBL_FILE에 정산서류 등록 */
	void insertSettleDoc(FileDTO settleDoc);
	
	/* TBL_FILE에 통장 사본 등록 */
	void insertAccountDoc(FileDTO accountDoc);
	
	/* TBL_FILE에 기타 서류 등록 */
	void insertEtcDoc(FileDTO etcDoc);
	
	
	/* 프로젝트 후기 등록 */
	void updateReview(ProjectDTO project);
	
	
	/* 좋아요 기능 */
	int loadIsLiked(LikeDTO likeProject);
	
	int isLikedByUser(LikeDTO likeProject);
	
	void insertProjectLike(LikeDTO likeProject);
	
	void deleteProjectLike(LikeDTO likeProject);


	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
