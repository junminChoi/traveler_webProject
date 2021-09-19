package com.traveler.main.controller.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.service.user.detail.UserDetailService;
import com.traveler.main.vo.reponse.ResVo;
import com.traveler.main.vo.user.PasswordVo;
import com.traveler.main.vo.user.SignInVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserDetailController {

	private final UserDetailService userDetailService;
	
	@GetMapping("/check/mail") /* 이메일 유효성 및 중복 체크 */
	public ResponseEntity<ResVo> checkMail(@RequestParam(value = "mail", required = false) @NotBlank @Email String userEmail) throws Exception {
		log.info("URI = /api/user/check/mail?mail={}", userEmail);
		
		if(!userDetailService.checkMail(userEmail))
			return ResponseEntity.ok().body(new ResVo(400, "중복 메일"));
		return ResponseEntity.ok().body(new ResVo(200, "SUCCESS"));	
	}
	
	@GetMapping("/check/nickname") /* 닉네임 유효성 및 중복 체크 */
	public ResponseEntity<ResVo> checkNickName(@RequestParam(value = "nickName", required = false) @NotBlank @Length(min = 2, max = 10) String userNickName) throws Exception {
		log.info("URI = /api/user/check/nickname?nickName={}", userNickName);
		
		if(!userDetailService.checkNickName(userNickName))
			return ResponseEntity.ok().body(new ResVo(400, "중복 닉네임"));
		return ResponseEntity.ok().body(new ResVo(200, "SUCCESS"));
	}
	
	@GetMapping("/check/password") /* 비밀번호 유효성 및 일치여부 체크 */
	public ResponseEntity<ResVo> checkPassword(@Validated @RequestBody PasswordVo passwordVo) throws Exception {
		log.info("URI = /api/user/check/password");
		
		if(!userDetailService.checkPassword(passwordVo))
			return ResponseEntity.ok().body(new ResVo(400, "비밀번호 불일치"));
		return ResponseEntity.ok().body(new ResVo(200, "SUCCESS"));
	}
	
	@PostMapping("/modify/password") /* 비밀번호 변경(찾기) */
	public ResponseEntity<ResVo> modifyPassword(@RequestParam(value = "mail", required = false) String userEmail,
												@Validated @RequestBody PasswordVo passwordVo) throws Exception {
		log.info("URI = /api/user/modify/password?mail={}", userEmail);
		
		if(!userDetailService.checkPassword(passwordVo))
			return ResponseEntity.ok().body(new ResVo(400, "비밀번호 불일치"));
		
		SignInVo user = new SignInVo(userEmail, passwordVo.getPasswd());
		userDetailService.modifyPassword(user);
		
		return ResponseEntity.ok().body(new ResVo(200, "SUCCESS"));
	}
	
}
