package com.greedy.togather.user.user.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.togather.common.exception.user.UserModifyException;
import com.greedy.togather.common.exception.user.UserRegistException;
import com.greedy.togather.common.exception.user.UserRemoveException;
import com.greedy.togather.user.project.dto.FileDTO;
import com.greedy.togather.user.project.dto.ReplyDTO;
import com.greedy.togather.user.user.model.dto.UserDTO;
import com.greedy.togather.user.user.service.AuthenticationService;
import com.greedy.togather.user.user.service.MailService;
import com.greedy.togather.user.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

	@Value("${image.image-dir}")
	private String IMAGE_DIR;

	private final PasswordEncoder passwordEncoder;
	private final MessageSourceAccessor messageSourceAccessor;
	private final UserService userService;
	private final AuthenticationService authenticationService;
	private final MailService mailService;

	public UserController(MessageSourceAccessor messageSourceAccessor, UserService userService,
			PasswordEncoder passwordEncoder, AuthenticationService authenticationService, MailService mailService) {
		this.messageSourceAccessor = messageSourceAccessor;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationService = authenticationService;
		this.mailService = mailService;

	}

	/* 로그인 */
	@GetMapping("/login")
	public String goLogin() {

		return "user/login/login";
	}

	/* 로그인 실패 */
	@PostMapping("/loginfail")
	public String loginFailed(RedirectAttributes rttr) {

		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));

		return "redirect:/user/login";
	}

	/* 아이디 찾기 */
	@GetMapping("/searchId")
	public String goSearchId() {

		return "user/login/searchId";
	}

	/* 이메일 전송 */
	@PostMapping("/mailsend")
	@ResponseBody
	String mailConfirm(@ModelAttribute UserDTO user, @RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "emailId", required = false) String emailId) throws Exception {

		String ph = phone;
		String email = emailId;
		log.info("[UserController] email : {}", email);

		String tempPwd = mailService.sendSimpleMessage(email); // 임시 비밀번호 발급

		// DB에 임시 비밀번호 저장
		UserDTO tempUser = userService.getUserByEmail(email);
		tempUser.setPwd(passwordEncoder.encode(tempPwd)); // 비밀번호 인코딩
		userService.modifyTpwd(tempUser);

		return tempPwd; // 클라이언트에게 발급된 임시 비밀번호 반환
	}

	/* 비밀번호 찾기 */
	@GetMapping("/searchPwd")
	public String goSearchPwd() {

		return "user/login/searchPwd";
	}

	/* 비밀번호 찾기 결과 */
	@GetMapping("/searchPwdResult")
	public String goSearchPwdResult() {

		return "user/login/searchPwdResult";
	}

	/* 회원 가입 페이지 */
	@GetMapping("/signup")
	public String goRegister() {

		return "user/login/signup";
	}

	/* 아이디 중복 확인 */
	@PostMapping("/idDupCheck")
	public ResponseEntity<String> checkDuplication(@RequestBody UserDTO user) {

		String result = "사용 가능한 아이디입니다.";
		log.info("[UserController] Request Check ID : {}", user.getEmail());

		if (userService.selectUserById(user.getEmail())) {
			log.info("[UserController] Already Exist");
			result = "중복 된 아이디가 존재합니다.";
		}

		return ResponseEntity.ok(result);
	}

	/* 회원 가입 */
	@PostMapping("/signup")
	public String registUser(@ModelAttribute UserDTO user, @RequestParam String address1, @RequestParam String address2,
			RedirectAttributes rttr) throws UserRegistException {

		String address = "$" + address1 + "$" + address2;
		user.setAddress(address);
		user.setPwd(passwordEncoder.encode(user.getPwd()));

		log.info("[UserController] registUser request User : " + user);

		userService.registUser(user);

		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("user.signup"));

		return "user/login/successSignUp";
	}

	/* 내 정보 수정페이지 */
	@GetMapping("/myInfo")
	public String goModifyUser(@AuthenticationPrincipal UserDTO loginUser, Model model) {

		String[] address = loginUser.getAddress().split("\\$");

		model.addAttribute("address", address);

		return "user/myPage/myInfo";
	}

	/* 회원 정보 수정 */
	@PostMapping("/myInfo")
	public String modifyUser(@ModelAttribute UserDTO updateUser, @RequestParam String address1,
			@RequestParam String address2, @AuthenticationPrincipal UserDTO loginUser, RedirectAttributes rttr)
			throws UserModifyException {

		String address = "$" + address1 + "$" + address2;
		updateUser.setAddress(address);
		updateUser.setUserNo(loginUser.getUserNo());

		log.info("[UserController] modifyUser request User : {}", updateUser);

		userService.modifyUser(updateUser);

		SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(loginUser.getEmail()));

		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("user.modify"));

		return "redirect:/";
	}

	/* 비밀 번호만 변경 */
	@PostMapping("/myInfo.pwd")
	public String modifyPwd(@ModelAttribute UserDTO updatePwd, @AuthenticationPrincipal UserDTO loginUser,
			RedirectAttributes rttr) throws UserModifyException {

		String encodedPwd = passwordEncoder.encode(updatePwd.getPwd());
		updatePwd.setPwd(encodedPwd);
		updatePwd.setUserNo(loginUser.getUserNo());

		log.info("[UserController] modifyPwd request User : {}", updatePwd);

		userService.modifyPwd(updatePwd);

		SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(loginUser.getEmail()));

		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("user.pwd"));

		return "redirect:/";

	}

	/* 회원 정보 수정 시 세션에 저장 된 정보 업데이트 */
	protected Authentication createNewAuthentication(String email) {

		UserDetails newPrincipal = authenticationService.loadUserByUsername(email);
		UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal,
				newPrincipal.getPassword(), newPrincipal.getAuthorities());

		return newAuth;

	}

	/* 회원 탈퇴 페이지 */
	@GetMapping("/withdrawal")
	public String goWithDrawal() {

		return "user/myPage/withdrawal";
	}

	/* 회원 탈퇴 */
	@GetMapping("/delete")
	public String deleteUser(@AuthenticationPrincipal UserDTO user, RedirectAttributes rttr)
			throws UserRemoveException {

		log.info("[UserController] user : " + user);

		userService.removeUser(user);

		SecurityContextHolder.clearContext();

		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("user.delete"));

		return "redirect:/";
	}

	/* 마이 페이지 */
	@GetMapping("/myPage")
	public String goMyPage() {

		return "user/myPage/myPage";
	}

	/* 프로필 사진 등록 */
	@PostMapping("/thumbnail/regist")
	public String registProfile(MultipartFile profileImage, @AuthenticationPrincipal UserDTO user,
			RedirectAttributes rttr) {

		log.info("[ProfileController] profileImage request : {}", profileImage);

		String fileUploadDir = IMAGE_DIR + "userProfile";

		File dir = new File(fileUploadDir);

		log.info("[ProfileController] dir : {}", dir);

		/* 디렉토리가 없을 경우 생성한다. */
		if (!dir.exists()) {
			dir.mkdirs();
		}

		try {

			/* 첨부파일이 실제로 있는 경우에만 로직 수행 */

			if (profileImage.getSize() > 0) {

				String originalFileName = profileImage.getOriginalFilename();
				log.info("[ProfileController] originalFileName : {}", originalFileName);

				String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
				String savedFileName = UUID.randomUUID().toString() + ext;

				log.info("[ProfileController] savedFileName : {}", savedFileName);

				/* 서버의 설정 디렉토리에 파일 저장하기 */
				profileImage.transferTo(new File(fileUploadDir + "/" + savedFileName));

				/* DB에 저장할 파일의 정보 처리 */
				FileDTO profile = new FileDTO();
				profile.setOriginalFileName(originalFileName);
				profile.setSavedFileName(savedFileName);
				profile.setFilePath("/upload/userProfile/");

				user.setProfileNm(fileUploadDir + "/" + savedFileName); // 유저 정보에 파일 정보를 저장
				log.info("[ProfileController] user.getProfileNm() : {}", user.getProfileNm());

				userService.registThumb(user);
			}

			rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("user.profile.regist"));

		} catch (Exception e) {
			e.printStackTrace();
			/* 실패 시 이미 저장 된 파일을 삭제한다. */
			if (user.getProfileNm() != null) {
				File deleteFile = new File(user.getProfileNm() + "/" + user.getProfileNm());
				deleteFile.delete();
			}
		}

		return "redirect:/";
	}

	/* 프로필 사진 삭제 */
	@PostMapping("/thumbnail/delete")
	public String deleteProfile(@AuthenticationPrincipal UserDTO user) {

		try {
			String profileNm = user.getProfileNm();

			if (profileNm != null) {
				/* 서버에 저장된 파일 삭제 */
				File deleteFile = new File(profileNm);
				deleteFile.delete();

				/* DB에서 파일 정보 삭제 */

				userService.deleteThumb(user);

				user.setProfileNm(null);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/user/myInfo";
	}

	
	  /* 프로필 사진 조회 */
	  @GetMapping("/thumbnail/regist") 
	  public String selectUserP (UserDTO user, Model model) {
	
		    UserDTO userProfile = userService.getUserProfile(user.getEmail());
		    String profileNm = userProfile.getProfileNm();
		    model.addAttribute("profileNm", profileNm);
		    
		  
		    return "user/myPage/myInfo";
		}

	

	/* 나의 프로젝트 */
	@GetMapping("/myProject")
	public String goMyProject() {

		return "user/myPage/myProject";
	}

	/* 내 정보 비밀번호 변경 */
	@GetMapping("/myInfo.pwd")
	public String goMyInfoPwd() {

		return "user/myPage/myInfo.pwd";

	}

	/* 아이디 찾기 */

	@PostMapping("/searchId")
	public String doFindIdSearch(@ModelAttribute UserDTO user, Model model) {
		log.info("[UserController] user : " + user);
		String email = userService.findLoginId(user);

		String[] emailSplit = email.split("@");
		String id = emailSplit[0];
		String domain = emailSplit[1];

		int idLength = id.length();
		String maskedId = id.substring(0, idLength / 2 - 1) + "**" + id.substring(idLength / 2 + 1);

		String maskedEmail = maskedId + "@" + domain;

		model.addAttribute("email", maskedEmail);
		log.info("[UserController] model : " + model);

		String result = "";
		if (email != null) {
			result = "user/login/searchIdResult";
		} else {
			result = "user/login/searchId";
		}
		return result;
	}

	/* 좋아요한 프로젝트 조회하기 */
	@GetMapping("/likedProject")
	public String likedProject(@RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false) String searchCondition, @RequestParam(required = false) String searchValue,
			@AuthenticationPrincipal UserDTO loginUser, @RequestParam(value = "userNo", required = false) String userNo,
			Model model) {

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		log.info("[userService] searchMap : {}", searchMap);

		Map<String, Object> likedProjectList = userService.selectLikeListProject(userNo, searchMap, page);
		log.info("likedProjectList : {}" + likedProjectList);
		log.info(userNo);

		model.addAttribute("likedProjectList", likedProjectList.get("likeProjectList"));
		model.addAttribute("paging", likedProjectList.get("paging"));

		return "user/myPage/likedProject";
	}
}
