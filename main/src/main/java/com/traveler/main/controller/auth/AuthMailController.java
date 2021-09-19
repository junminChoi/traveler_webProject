package com.traveler.main.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.service.auth.AuthMailService;
import com.traveler.main.vo.auth.CertifyKeyVo;
import com.traveler.main.vo.reponse.ResVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class AuthMailController {

	private final AuthMailService authMailService;
	
	@PostMapping("/send") /* 인증번호 메일 전송 및 재전송 */
	public ResponseEntity<ResVo> sendMail(@RequestParam(value = "mail", required = false) String userEmail) throws Exception {
		log.info("URI = /api/mail/send?mail={}", userEmail);
		
		if(authMailService.sendMail(userEmail))
			return ResponseEntity.ok().body(new ResVo(200, "SUCCESS"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResVo(500, "FAIL"));
	}
	
	@GetMapping("/valid") /* 인증번호 유효성 체크 */
	public ResponseEntity<ResVo> validateTimeCertifyKey(@Validated @RequestBody CertifyKeyVo certifyKeyVo) throws Exception {
		log.info("URI = /api/mail/valid");
		
		if(authMailService.validateTimeCertifyKey(certifyKeyVo))
			return ResponseEntity.ok().body(new ResVo(200, "SUCCESS"));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResVo(400, "FAIL"));
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
