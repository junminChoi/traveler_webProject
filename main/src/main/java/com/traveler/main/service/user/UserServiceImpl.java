package com.traveler.main.service.user;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.traveler.main.jwt.JwtTokenProvider;
import com.traveler.main.mapper.token.TokenMapper;
import com.traveler.main.mapper.user.UserMapper;
import com.traveler.main.vo.token.TokenInfoVo;
import com.traveler.main.vo.user.SignInVo;
import com.traveler.main.vo.user.SignUpVo;
import com.traveler.main.vo.user.UserVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final TokenMapper tokenMapper;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	
	@Override /* 회원가입 */
	public void signUp(SignUpVo signUpVo) throws Exception {
		UserVo userVo = new UserVo();
		userVo.setMail(signUpVo.getMail());
		userVo.setNickName(signUpVo.getNickName());
		userVo.setPasswd(passwordEncoder.encode(signUpVo.getPasswd()));
		
		userMapper.signUp(userVo);
	}

	@Override /* 로그인 */
	public String signIn(SignInVo signInVo) throws Exception {
		UserVo userVo = userMapper.userInfo(signInVo.getMail());
		
		if(Objects.isNull(userVo) || !passwordEncoder.matches(signInVo.getPasswd(), userVo.getPasswd()))
			return "FAIL";
		
		String token = jwtTokenProvider.createToken(userVo);
		tokenMapper.saveToken(new TokenInfoVo(userVo.getMail(), token));
		return token;
	}

	@Override /* 회원정보 조회 */
	public UserVo userInfo(String userEmail) throws Exception {
		return userMapper.userInfo(userEmail);
	}

}
