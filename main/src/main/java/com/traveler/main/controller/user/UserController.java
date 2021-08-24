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

import com.traveler.main.service.authentication.AuthMailService;
import com.traveler.main.service.token.TokenService;
import com.traveler.main.service.user.UserService;
import com.traveler.main.service.user.detail.UserDetailService;
import com.traveler.main.vo.authentication.CertifyKeyVo;
import com.traveler.main.vo.reponse.ResponseDataVo;
import com.traveler.main.vo.reponse.ResponseVo;
import com.traveler.main.vo.token.TokenVo;
import com.traveler.main.vo.user.PasswordVo;
import com.traveler.main.vo.user.SignInVo;
import com.traveler.main.vo.user.SignUpVo;
import com.traveler.main.vo.user.UserVo;

import lombok.RequiredArgsConstructor;

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
	public ResponseEntity<ResponseVo> signUp(@Validated @RequestBody SignUpVo signUpVo) throws Exception {
		
		if(!userDetailService.checkMail(signUpVo.getUserEmail()))
			return ResponseEntity.ok().body(new ResponseVo(400, "메일을 다시 확인해주세요"));
		
		if(!authMailService.validateCertifyKey(new CertifyKeyVo(signUpVo.getUserEmail(), signUpVo.getCertifyKey())))
			return ResponseEntity.ok().body(new ResponseVo(400, "인증번호를 다시 확인해주세요"));
		
		if(!userDetailService.checkNickName(signUpVo.getUserNickName()))
			return ResponseEntity.ok().body(new ResponseVo(400, "닉네임을 다시 확인해주세요"));
		
		if(!userDetailService.checkPassword(new PasswordVo(signUpVo.getUserPW(), signUpVo.getUserPWchk())))
			return ResponseEntity.ok().body(new ResponseVo(400, "비밀번호를 다시 확인해주세요"));
		
		userService.signUp(signUpVo);
		return ResponseEntity.ok().body(new ResponseVo(200, "회원가입 성공"));
	}
	
	@PostMapping("/signin") /* 로그인 */
	public ResponseEntity<Object> signIn(@Validated @RequestBody SignInVo signInVo) throws Exception {
		String object = userService.signIn(signInVo);
		
		if(object.equals("FAIL"))
			return ResponseEntity.ok().body(new ResponseVo(400, "로그인 실패"));
		
		Map<String, String> token = new HashMap<>();
		token.put("tokenType", TOKEN_TYPE);
		token.put("accessToken", object);
		
		return ResponseEntity.ok().body(new TokenVo(200, "로그인 성공", token));
	}
	
	@GetMapping("/myinfo") /* 마이페이지 */ /* 토큰 인증 */
	public ResponseEntity<ResponseDataVo> myPage(HttpServletRequest request) throws Exception {
		String userEmail = (String) request.getAttribute("userEmail");
		
		UserVo userVo = userService.userInfo(userEmail);
		
		Map<String, String> user = new HashMap<>();
		user.put("userEmail", userVo.getUserEmail());
		user.put("userNickName", userVo.getUserNickName());
		
		
		return ResponseEntity.ok().body(new ResponseDataVo(200, "마이페이지 불러오기 성공", user));
	}
	
	@PostMapping("/signout") /* 로그아웃 */ /* 토큰 인증 */
	public ResponseEntity<ResponseVo> signout(HttpServletRequest request) throws Exception {
		String userEmail = (String) request.getAttribute("userEmail");
		
		tokenService.removeToken(userEmail);
		
		return ResponseEntity.ok().body(new ResponseVo(200, "로그아웃 성공"));
	}
	
}
