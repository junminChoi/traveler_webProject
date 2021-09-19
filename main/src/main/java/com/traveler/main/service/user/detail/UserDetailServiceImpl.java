package com.traveler.main.service.user.detail;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.traveler.main.mapper.user.UserDetailMapper;
import com.traveler.main.vo.user.PasswordVo;
import com.traveler.main.vo.user.SignInVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {

	private final UserDetailMapper userDetailMapper;
	private final PasswordEncoder passwordEncoder;
	
	@Override /* 메일 중복 체크 */
	public boolean checkMail(String userEmail) throws Exception {
		int check = userDetailMapper.checkMail(userEmail);
		
		if(check != 0)
			return false;
		return true;
	}

	@Override /* 닉네임 중복 체크 */
	public boolean checkNickName(String userNickName) throws Exception {
		int check = userDetailMapper.checkNickName(userNickName);
		
		if(check != 0)
			return false;
		return true;
	}

	@Override /* 비밀번호 일치여부 체크 */
	public boolean checkPassword(PasswordVo passwordVo) throws Exception {
		
		if(!passwordVo.getPasswd().equals(passwordVo.getPasswdChk()))
			return false;
		return true;
	}

	@Override /* 비밀번호 변경 */
	public void modifyPassword(SignInVo user) throws Exception {
		
		SignInVo enUser = new SignInVo();
		enUser.setMail(user.getMail());
		enUser.setPasswd(passwordEncoder.encode(user.getPasswd()));
		
		userDetailMapper.modifyPassword(enUser);
	}

}
