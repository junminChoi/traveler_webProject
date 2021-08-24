package com.traveler.main.service.user.detail;

import com.traveler.main.vo.user.PasswordVo;
import com.traveler.main.vo.user.SignInVo;

public interface UserDetailService {

	public boolean checkMail(String userEmail) throws Exception;
	
	public boolean checkNickName(String userNickName) throws Exception;
	
	public boolean checkPassword(PasswordVo passwordVo) throws Exception;
	
	public void modifyPassword(SignInVo user) throws Exception;
	
}
