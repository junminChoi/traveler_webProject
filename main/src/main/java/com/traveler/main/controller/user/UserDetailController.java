package com.traveler.main.controller.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.service.user.detail.UserDetailService;
import com.traveler.main.vo.reponse.ResponseVo;
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
	
	@GetMapping("/check/mail/{userEmail:.+}") /* 이메일 유효성 및 중복 체크 */
	public ResponseEntity<ResponseVo> checkMail(@PathVariable @NotBlank @Email String userEmail) throws Exception {
		log.info("[/api/user/check/mail/{}]");
		
		if(!userDetailService.checkMail(userEmail))
			return ResponseEntity.ok().body(new ResponseVo(400, "중복 메일"));
		return ResponseEntity.ok().body(new ResponseVo(200, "사용 가능한 메일"));	
	}
	
	@GetMapping("/check/nickname/{userNickName}") /* 닉네임 유효성 및 중복 체크 */
	public ResponseEntity<ResponseVo> checkNickName(@PathVariable @NotBlank @Length(min = 2, max = 10) String userNickName) throws Exception {
		log.info("[/api/user/check/nickname/{}]");
		
		if(!userDetailService.checkNickName(userNickName))
			return ResponseEntity.ok().body(new ResponseVo(400, "중복 닉네임"));
		return ResponseEntity.ok().body(new ResponseVo(200, "사용 가능한 닉네임"));
	}
	
	@GetMapping("/check/password") /* 비밀번호 유효성 및 일치여부 체크 */
	public ResponseEntity<ResponseVo> checkPassword(@Validated @RequestBody PasswordVo passwordVo) throws Exception {
		log.info("[/api/user/check/password]");
		
		if(!userDetailService.checkPassword(passwordVo))
			return ResponseEntity.ok().body(new ResponseVo(400, "비밀번호 불일치"));
		return ResponseEntity.ok().body(new ResponseVo(200, "비밀번호 일치"));
	}
	
	@PostMapping("/modify/password/{userEmail}") /* 비밀번호 변경(찾기) */
	public ResponseEntity<ResponseVo> modifyPassword(@PathVariable String userEmail, @Validated @RequestBody PasswordVo passwordVo) throws Exception {
		log.info("[/api/user/modify/password/{}]");
		
		if(!userDetailService.checkPassword(passwordVo))
			return ResponseEntity.ok().body(new ResponseVo(400, "비밀번호 불일치"));
		
		SignInVo user = new SignInVo(userEmail, passwordVo.getUserPW());
		userDetailService.modifyPassword(user);
		
		return ResponseEntity.ok().body(new ResponseVo(200, "비밀번호 변경 성공"));
	}
	
}
