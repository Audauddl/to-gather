package com.greedy.togather.user.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.togather.user.project.dto.FileDTO;
import com.greedy.togather.user.project.dto.LikeDTO;
import com.greedy.togather.user.project.dto.MakerDTO;
import com.greedy.togather.user.project.dto.ProjectDTO;
import com.greedy.togather.user.project.dto.ReplyDTO;
import com.greedy.togather.user.project.service.ProjectService;
import com.greedy.togather.user.user.model.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Controller
@RequestMapping("/project")
public class ProjectController {
	
	/* @Value : application.yml 파일의 키 값으로 디렉토리 읽어옴 (넘어온 이미지 파일을 저장하기 위한 필드) */
	@Value("${image.image-dir}")
	private String IMAGE_DIR;
	
	private final ProjectService projectService;
	private final MessageSourceAccessor messageSourceAccessor;
	
	public ProjectController(ProjectService projectService, MessageSourceAccessor messageSourceAccessor) {
		this.projectService = projectService;
		this.messageSourceAccessor = messageSourceAccessor;
	}
	
	/* 검색한 키워드를 조회한 페이지로의 이동 */
	@GetMapping("/searched")
	public String goSearchedProject(@RequestParam(name="word") String word, Model model) {
		
		log.info(word);
		
		Map<String, Object> allProjectList = projectService.selectSearchedProjectList(word);
		model.addAttribute("searchedWord", word);
		model.addAttribute("searchedProjectList", allProjectList.get("searchedProjectList"));
		
		return "user/project/viewProjects/viewSearchedProjects";
	}
	
	
	/* 카테고리별 프로젝트 페이지로의 이동 */
	@GetMapping("/education")
	public String goEducation(Model model) {
		
		String categoryCode ="CA001";
		String category = "education";
		
		Map<String, Object> allProjectList = projectService.selectProjectList(categoryCode);
		model.addAttribute("projectList", allProjectList.get("projectList"));
		model.addAttribute("category", category);
		
		return "user/project/viewProjects/viewSelectedProjects";
	}
	
	@GetMapping("/disaster")
	public String goDisaster(Model model) {
		
		String categoryCode ="CA002";
		String category = "disaster";
		
		Map<String, Object> allProjectList = projectService.selectProjectList(categoryCode);
		model.addAttribute("projectList", allProjectList.get("projectList"));
		model.addAttribute("category", category);
		
		return "user/project/viewProjects/viewSelectedProjects";
	}
	
	@GetMapping("/environment")
	public String goEnvironment(Model model) {
		
		String categoryCode ="CA003";
		String category = "environment";
		
		Map<String, Object> allProjectList = projectService.selectProjectList(categoryCode);
		model.addAttribute("projectList", allProjectList.get("projectList"));
		model.addAttribute("category", category);
		
		return "user/project/viewProjects/viewSelectedProjects";
	}
	
	@GetMapping("/medical")
	public String goMedical(Model model) {
		
		String categoryCode ="CA004";
		String category = "medical";
		
		Map<String, Object> allProjectList = projectService.selectProjectList(categoryCode);
		model.addAttribute("projectList", allProjectList.get("projectList"));
		model.addAttribute("category", category);
		
		return "user/project/viewProjects/viewSelectedProjects";
	}
	
	@GetMapping("/organic")
	public String goOrganic(Model model) {	
		
		String categoryCode ="CA005";
		String category = "organic";
		
		Map<String, Object> allProjectList = projectService.selectProjectList(categoryCode);
		model.addAttribute("projectList", allProjectList.get("projectList"));
		model.addAttribute("category", category);
		
		return "user/project/viewProjects/viewSelectedProjects";
	}
	
	@GetMapping("/wild")
	public String goWild(Model model) {	
		
		String categoryCode ="CA006";
		String category = "wild";
		
		Map<String, Object> allProjectList = projectService.selectProjectList(categoryCode);
		model.addAttribute("projectList", allProjectList.get("projectList"));
		model.addAttribute("category", category);
		
		return "user/project/viewProjects/viewSelectedProjects";
	}
	
	@GetMapping("/pet")
	public String goPet(Model model) {	
		
		String categoryCode ="CA007";
		String category = "pet";
		
		Map<String, Object> allProjectList = projectService.selectProjectList(categoryCode);
		model.addAttribute("projectList", allProjectList.get("projectList"));
		model.addAttribute("category", category);

		return "user/project/viewProjects/viewSelectedProjects";
	}
	
	
	/* ------------------------------------------------------------------------------------------------------ */
	
	/* 프로젝트 신청 페이지 */
	@GetMapping("/create")
	public String goToCreateProject() {
		
		return "user/project/createProject/createProject";
	}
	
	@PostMapping("/create")
	public String createProject(ProjectDTO project, MakerDTO maker, @AuthenticationPrincipal UserDTO writer,
								MultipartFile makerProfile, MultipartFile mainImage,  List<MultipartFile> subImageList, 
								MultipartFile settleDoc, MultipartFile accountDoc, MultipartFile etcDoc,
								@RequestParam String zipCode, @RequestParam String address1, @RequestParam String address2) {
		
		/* 주소 가공 */
		String address = zipCode + "$" + address1 + "$" + address2;
    	maker.setMakerAddress(address);
    	log.info("[ProjectController] address : {}", address);
		
		/* 프로젝트 신청 시, 저장될 경로 분리 */
		String fileUploadDir = IMAGE_DIR + "original";			// 프로젝트 관련 이미지, 서류들
		String makerProfileDir = IMAGE_DIR + "makerProfile";	// maker의 프로필 사진
		String documentDir = IMAGE_DIR + "document";			// 정산과 관련된 서류들
		
		File dir1 = new File(fileUploadDir);
		File dir2 = new File(makerProfileDir);
		File dir3 = new File(documentDir);
		
		
		/* 디렉토리가 없을 경우 생성 */
		if(!dir1.exists() || !dir2.exists() || !dir3.exists()) {
			dir1.mkdirs();
			dir2.mkdirs();
			dir3.mkdirs();
		}
		
		/* 아래의 processedFile() 메소드 호출하여 가공 */
		FileDTO processedMakerProfile = processedFile(makerProfile, makerProfileDir, "메이커프로필");
		FileDTO processedMainImage = processedFile(mainImage, fileUploadDir, "대표사진");
		FileDTO processedSettleDoc = processedFile(settleDoc, documentDir, "정산서류");
		FileDTO processedAccountDoc = processedFile(accountDoc, documentDir, "통장사본");
		FileDTO processedEtcDoc = processedFile(etcDoc, documentDir, "기타서류");
		/* List형태인 subImage는 반복문을 통해 하나씩 호출 */
		List<FileDTO> processedSubImageList = new ArrayList<>();
		
		for(int i = 0; i < subImageList.size(); i++) {
			FileDTO processedSubImage = processedFile(subImageList.get(i), fileUploadDir, "서브사진");
			log.info("[ProjectController] processedSubImage : {}", processedSubImage); 
			/* processedSubImage가 null이 아닐 때만 담기도록 조건문 설정 (호출한 뒤 반환받은 fileInfo가 null이라는 것은 이미지 첨부가 되지 않았음을 의미 - 서브사진은 nullable) */
			if(processedSubImage != null) {
				log.info("[ProjectController] processedSubImage : {}", processedSubImage); 
				processedSubImageList.add(processedSubImage);
			}
		}
		
		/* 메이커 프로필은 TBL_MAKER에도 저장 */
		maker.setMakerProfileName("/upload/makerProfile/" + processedMakerProfile.getSavedFileName());
		log.info("[ProjectController] maker.getMakerProfileName() : {}", maker.getMakerProfileName());
		
		/* DB와의 연결 */
		project.setWriter(writer);
		project.setProcessedMakerProfile(processedMakerProfile);
		project.setProcessedMainImage(processedMainImage);
		project.setProcessedSubImageList(processedSubImageList);
		project.setProcessedSettleDoc(processedSettleDoc);
		project.setProcessedAccountDoc(processedAccountDoc);
		project.setProcessedEtcDoc(processedEtcDoc);

		
		/* 저장한 값 Service에 보내기 */
		projectService.createProject(project, maker);
		
		return "redirect:/project/submit";
	}
	
	/* 멀티파트 파일과 저장 경로를 매개변수로 하고 FileDTO로 가공해서 반환 해주는 메소드 */
	public FileDTO processedFile(MultipartFile file, String filePath, String fileType) {
		
		/* 대표사진 가공 시, 저장될 썸네일 경로 설정 */
		String thumbnailDir = IMAGE_DIR + "thumbnail";
		File dir4 = new File(thumbnailDir);
		
		/* 디렉토리가 없을 경우 생성 */
		if(!dir4.exists()) {
			dir4.mkdirs();
		}
		
		FileDTO fileInfo = null;
		
		try {
			/* 첨부파일이 있는 경우에만 로직 수행 */
			if(file.getSize() > 0) {
							
				String originalFileName = file.getOriginalFilename();
				log.info("[ProjectController] originalFileName : {}", originalFileName);
							
				/* SavedFileName을 만들기 위해 첨부파일의 확장자 떼내기 */
				String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
				String savedFileName = UUID.randomUUID().toString() + ext;
									
				log.info("[ProjectController] savedFileName : {}", savedFileName);
						
				/* 서버의 설정 디렉토리에 파일 저장 (예외처리) */
				file.transferTo(new File(filePath + "/" + savedFileName));
							
				/* DB에 저장할 파일의 정보 처리 */
				fileInfo = new FileDTO();
				fileInfo.setOriginalFileName(originalFileName);
				fileInfo.setSavedFileName(savedFileName);
				fileInfo.setFileType(fileType);
						
				if(fileType.equals("대표사진")) {
					fileInfo.setFilePath("/upload/original/");
					/* 썸네일 처리 */
					Thumbnails.of(filePath + "/" + savedFileName)
					.size(220, 165)
					.toFile(thumbnailDir + "/thumbnail_" + savedFileName);
					fileInfo.setThumPath("/upload/thumbnail/thumbnail_" + savedFileName);
					
				} else if(fileType.equals("서브사진")) {
					fileInfo.setFilePath("/upload/original/");
					
				} else if(fileType.equals("정산서류") || fileType.equals("통장사본") || fileType.equals("기타서류")) {
					fileInfo.setFilePath("/upload/document/");
					
				} else if(fileType.equals("메이커프로필")) {
					fileInfo.setFilePath("/upload/makerProfile/");
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
					
				/* 실패 시 이미 저장 된 파일 삭제 */
				File deleteFile = new File(fileInfo.getFilePath() + "/" + fileInfo.getSavedFileName());
				File deleteThumbnail = new File(thumbnailDir + "/thumbnail_" + fileInfo.getSavedFileName());
						
				deleteFile.delete();
				deleteThumbnail.delete();
			}
		
		log.info("[ProjectController] fileInfo : {}", fileInfo);
		/* originalFileName, savedFileName, filePath, fileType, thumPath 담아 리턴 */
		return fileInfo;	
	}
	
	/* 프로젝트 최종 제출 후 페이지 */
	@GetMapping("/submit") 
	public String submitProject() {
		
		return "/user/project/createProject/submitProject";
	}
	
	/* ------------------------------------------------------------------------------------------------------ */
	
	/* 프로젝트 상세 페이지 */
	@GetMapping("/detail")
	public String viewProjectDetail(@RequestParam(value="projNo", required=false) String projNo, LikeDTO likeProject, 
									@AuthenticationPrincipal UserDTO user, Model model) {
		
		/* 현재 로그인 유저가 해당 프로젝트를 좋아요 했는가? */
		likeProject.setUser(user);
		likeProject.setProjNo(projNo);
		
		/* 프로젝트 상세 내용 조회 */
		Map<String, Object> allProjectDetails = projectService.selectProjectDetail(projNo, likeProject);
		log.info("[ProjectController] allProjectDetails : {}", allProjectDetails);
		
		model.addAttribute("detail", allProjectDetails.get("projectDetail"));
		model.addAttribute("rewardList", allProjectDetails.get("rewardList"));
		model.addAttribute("donationAndReplyCount", allProjectDetails.get("donationAndReplyCount"));
		model.addAttribute("loadIsLiked", allProjectDetails.get("loadIsLiked"));
		
	    log.info("[ProjectController] 프로젝트 상세 페이지의 loadIsLiked : {}", allProjectDetails.get("loadIsLiked"));

		
		return "user/project/viewProjectDetail/viewProjectDetail";
	}
	
	/* 댓글 조회(비동기통신) */
	@GetMapping("/loadReply")
	public ResponseEntity<List<ReplyDTO>> viewReplyList(ReplyDTO loadReply, Model model) {
		
		log.info("[ProjectController] loadReply : {}", loadReply);
		
		List<ReplyDTO> replyList = projectService.selectReplyList(loadReply);
		log.info("[ProjectController] replyList : {}", replyList); /* 조회된 최신 댓글 확인 */
		
		model.addAttribute("replyList", replyList);
		
		return ResponseEntity.ok(replyList);
	}
	
	/* 댓글 등록(비동기통신) */
	@PostMapping("/registReply")
	public ResponseEntity<String> registReply(@RequestBody ReplyDTO registReply,
			  								  @AuthenticationPrincipal UserDTO writer) {
		log.info("[ProjectController] registReply : {}", registReply);
		log.info("[ProjectController] writer : {}", writer);
		
		/* 랜덤 댓글 기부금 설정 */
		int random = (int)(Math.random() * 2) + 1; 
		int donation = (random == 1) ? 100 : 0;
		log.info("[ProjectController] donation : {}", donation);
		
		/* registReply에는 projNo, replyBody만 담겨 있는 상태 */
		registReply.setWriter(writer); 		// 댓글 작성자 = 로그인 유저
		registReply.setDonation(donation);	// 랜덤 댓글 기부금
		
		projectService.insertReply(registReply);
		
		return ResponseEntity.ok("댓글 등록 완료💟");
	}
	
	/* 프로젝트 공유 페이지 */
	@GetMapping("/share")
	public String shareProject() {
		return "/user/project/viewProjectDetail/shareProject";
	}
	
	/* ------------------------------------------------------------------------------------------------------ */
	
	/* 프로젝트 후기 작성 */
	@GetMapping("/review")
	public String goToWriteReview(@RequestParam(value="projNo", required=false) String projNo, ProjectDTO project, Model model, RedirectAttributes rttr) {
		
		log.info("[ProjectController] 프로젝트 후기(GET) 의 projNo : {}", projNo);
		
		model.addAttribute("projNo", projNo);
		
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("write.review"));
		
		return "/user/project/writeReview/writeReview";
	}
	
	@PostMapping("/review")
	public String WriteReview(@RequestParam(name="projNo") String projNo, ProjectDTO project, @RequestParam String reviewBody, RedirectAttributes rttr) {
		
		log.info("[ProjectController] 프로젝트 후기의 project : {}", project);
		log.info("[ProjectController] 프로젝트 후기(POST)의 projNo : {}", projNo);
		log.info("[ProjectController] 프로젝트 후기의 reviewBody : {}", reviewBody);
		
		/* 해당 프로젝트의 projNo도 함께 전송 */
		project.setProjNo(projNo);
		project.setProjReview(reviewBody);
		projectService.updateReview(project);
		
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("write.review"));
		
		return "redirect:/";
		
	}
	
	/* ------------------------------------------------------------------------------------------------------ */
	
	/* 프로젝트 좋아요 */
	@PostMapping("/like")
	public ResponseEntity<String> likeProject(@RequestBody LikeDTO likeProject, @AuthenticationPrincipal UserDTO user, Model model) {
		
		/* likeProject에는 projNo만 담겨있는 상태 */
		likeProject.setUser(user); // 좋아요한 유저 = 로그인 유저
	    
	    int isLiked = projectService.isLikedByUser(likeProject);
	    log.info("[ProjectController] 프로젝트 좋아요의 isLiked : {}", isLiked);
	    
	    String result = null;
	    
	    if(isLiked == 1) { /* 이미 좋아요가 되어있을 경우 */
	        result = "unliked";
	    } else { /* 좋아요가 되어있지 않은 경우 */
	    	result = "liked";
	    }
	    log.info("[ProjectController] 프로젝트 좋아요의 result : {}", result);
	    return ResponseEntity.ok(result);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
