package com.traveler.main.service.authentication;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.traveler.main.mapper.AuthMailMapper;
import com.traveler.main.vo.authentication.CertifyKeyVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthMailServiceImpl implements AuthMailService {

	private final AuthMailMapper authMailMapper;
	private final JavaMailSender javaMailSender;
	
	/* Mail Sender */
	private int mail(String userEmail) {
		Random rand = new Random();
		int certifyKey = rand.nextInt(900000) + 100000; // 인증번호 : 000001 ~ 999999
		
		String setfrom = "Traveler <anonymous10520@gmail.com>";
		String tomail = userEmail;
		String title = "Traveler 회원가입 인증번호";
		String content = System.getProperty("line.separator") + "인증번호는 " + certifyKey + " 입니다.";
		
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom(setfrom);
			messageHelper.setTo(tomail);
			messageHelper.setSubject(title);
			messageHelper.setText(content);
		  
			javaMailSender.send(message);
		} catch(MessagingException e) {
			log.error("[Fail to Mail] ClassName = {}, Message = {}", e.getClass().getSimpleName(), e.getMessage());
			return -1;
		}
		return certifyKey;
	}
	
	@Override /* 인증번호 메일 발송 */
	public boolean sendMail(String userEmail) throws Exception {
		
		int certifyKey = mail(userEmail);
		
		if(certifyKey != -1) {
			CertifyKeyVo certifyKeyVo = new CertifyKeyVo(userEmail, certifyKey);
			authMailMapper.saveCertifyKey(certifyKeyVo);
			
			return true;
		}
		return false;
	}

//	@Override /* 인증번호 메일 재발송 */
//	public boolean resendMail(String userEmail) throws Exception {
//		int certifyKey = mail(userEmail);
//		
//		if(certifyKey != -1) {
//			CertifyKeyVo certifyKeyVo = new CertifyKeyVo(userEmail, certifyKey);
//			authMailMapper.updateCertifykey(certifyKeyVo);
//			
//			return true;
//		}
//		return false;
//	}

	@Override /* 인증번호 유효성 체크 (시간 유효성 O) */
	public boolean validateTimeCertifyKey(CertifyKeyVo certifyKeyVo) throws Exception {
		int check = authMailMapper.validateTimeCertifyKey(certifyKeyVo);
		
		if(check == 1)
			return true;
		return false;
	}

	@Override /* 인증번호 유효성 체크 (시간 유효성 X) */
	public boolean validateCertifyKey(CertifyKeyVo certifyKeyVo) throws Exception {
		int check = authMailMapper.validateCertifyKey(certifyKeyVo);
		
		if(check == 1)
			return true;
		return false;
	}

}
