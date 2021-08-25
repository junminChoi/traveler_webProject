package com.traveler.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.service.authentication.AuthMailService;
import com.traveler.main.vo.authentication.CertifyKeyVo;
import com.traveler.main.vo.reponse.ResponseVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthMailController {

	private final AuthMailService authMailService;
	
	@PostMapping("/mail/{userEmail:.+}") /* 인증번호 메일 전송 및 재전송 */
	public ResponseEntity<ResponseVo> sendMail(@PathVariable String userEmail) throws Exception {
		log.info("[/api/auth/mail/{}]");
		
		if(authMailService.sendMail(userEmail))
			return ResponseEntity.ok().body(new ResponseVo(200, "메일발송 성공"));
		return ResponseEntity.ok().body(new ResponseVo(500, "메일발송 실패"));
	}
	
	@GetMapping("/valid") /* 인증번호 유효성 체크 */
	public ResponseEntity<ResponseVo> validateTimeCertifyKey(@Validated @RequestBody CertifyKeyVo certifyKeyVo) throws Exception {
		log.info("[/api/auth/valid]");
		
		if(authMailService.validateTimeCertifyKey(certifyKeyVo))
			return ResponseEntity.ok().body(new ResponseVo(200, "메일인증 성공"));
		return ResponseEntity.ok().body(new ResponseVo(400, "메일인증 실패"));
	}
	
//	@PostMapping("/mail/{resend}") /* 인증번호 메일 전송 및 재전송 */
//	public ResponseEntity<ResponseVo> sendMail(@PathVariable boolean resend, @RequestBody String userEmail) throws Exception {
//		boolean check;
//		
//		if(resend)
//			check = authMailService.resendMail(userEmail);
//		else
//			check = authMailService.sendMail(userEmail);
//		
//		if(check)
//			return ResponseEntity.ok().body(new ResponseVo(200, "메일발송 성공"));
//		return ResponseEntity.ok().body(new ResponseVo(500, "메일발송 실패"));
//	}

}
