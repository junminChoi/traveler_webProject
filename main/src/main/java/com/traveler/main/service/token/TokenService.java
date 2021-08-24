package com.traveler.main.service.token;

import com.traveler.main.vo.token.TokenInfoVo;

public interface TokenService {

	public boolean checkToken(TokenInfoVo tokenInfoVo) throws Exception;
	
	public void removeToken(String userEmail) throws Exception;
	
}
