package com.traveler.main.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.service.auth.AuthMailService;
import com.traveler.main.service.token.TokenService;
import com.traveler.main.service.user.UserService;
import com.traveler.main.service.user.detail.UserDetailService;
import com.traveler.main.vo.auth.CertifyKeyVo;
import com.traveler.main.vo.reponse.ResDataVo;
import com.traveler.main.vo.reponse.ResVo;
import com.traveler.main.vo.token.TokenVo;
import com.traveler.main.vo.user.PasswordVo;
import com.traveler.main.vo.user.SignInVo;
import com.traveler.main.vo.user.SignUpVo;
import com.traveler.main.vo.user.UserVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserDetailService userDetailService;
	private final AuthMailService authMailService;
	private final TokenService tokenService;
	
	@Value("${jwt.type}")
	private String TOKEN_TYPE;
	
	@PostMapping("/signup") /* 회원가입 */
	public ResponseEntity<ResVo> signUp(@Validated @RequestBody SignUpVo signUpVo) throws Exception {
		log.info("URI = /api/signup");
		
		if(!userDetailService.checkMail(signUpVo.getMail()))
			return ResponseEntity.ok().body(new ResVo(400, "메일을 다시 확인해주세요"));
		
		if(!authMailService.validateCertifyKey(new CertifyKeyVo(signUpVo.getMail(), signUpVo.getCertifyKey())))
			return ResponseEntity.ok().body(new ResVo(400, "인증번호를 다시 확인해주세요"));
		
		if(!userDetailService.checkNickName(signUpVo.getNickName()))
			return ResponseEntity.ok().body(new ResVo(400, "닉네임을 다시 확인해주세요"));
		
		if(!userDetailService.checkPassword(new PasswordVo(signUpVo.getPasswd(), signUpVo.getPasswdChk())))
			return ResponseEntity.ok().body(new ResVo(400, "비밀번호를 다시 확인해주세요"));
		
		userService.signUp(signUpVo);
		return ResponseEntity.ok().body(new ResVo(200, "SUCCESS"));
	}
	
	@PostMapping("/signin") /* 로그인 */
	public ResponseEntity<Object> signIn(@Validated @RequestBody SignInVo signInVo) throws Exception {
		String object = userService.signIn(signInVo);
		
		if(object.equals("FAIL"))
			return ResponseEntity.ok().body(new ResVo(400, "FAIL"));
		
		Map<String, String> token = new HashMap<>();
		token.put("tokenType", TOKEN_TYPE);
		token.put("accessToken", object);
		
		log.info("URI = /api/signin, Mail= {}", signInVo.getMail());
		return ResponseEntity.ok().body(new TokenVo(200, "SUCCESS", token));
	}
	
	@GetMapping("/myinfo") /* 마이페이지 */ /* 토큰 인증 */
	public ResponseEntity<ResDataVo> myPage(HttpServletRequest request) throws Exception {
		log.info("URI = /api/myinfo");
		String userEmail = (String) request.getAttribute("userEmail");
		
		UserVo userVo = userService.userInfo(userEmail);
		
		Map<String, Object> map = new HashMap<>();
		Map<String, String> user = new HashMap<>();
		user.put("mail", userVo.getMail());
		user.put("nickName", userVo.getNickName());
		map.put("info", user);
		
		return ResponseEntity.ok().body(new ResDataVo(200, "SUCCESS", map));
	}
	
	@PostMapping("/signout") /* 로그아웃 */ /* 토큰 인증 */
	public ResponseEntity<ResVo> signout(HttpServletRequest request) throws Exception {
		String userEmail = (String) request.getAttribute("userEmail");
		
		tokenService.removeToken(userEmail);
		
		log.info("URI = /api/signout, Mail= {}", userEmail);
		return ResponseEntity.ok().body(new ResVo(200, "SUCCESS"));
	}
	
}
