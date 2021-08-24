package com.traveler.main.service.user;

import com.traveler.main.vo.user.SignInVo;
import com.traveler.main.vo.user.SignUpVo;
import com.traveler.main.vo.user.UserVo;

public interface UserService {

	public void signUp(SignUpVo signUpVo) throws Exception;
	
	public String signIn(SignInVo signInVo) throws Exception;
	
	public UserVo userInfo(String userEmail) throws Exception;
}
