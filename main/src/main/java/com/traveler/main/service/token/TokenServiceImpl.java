package com.traveler.main.service.token;

import org.springframework.stereotype.Service;

import com.traveler.main.mapper.token.TokenMapper;
import com.traveler.main.vo.token.TokenInfoVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final TokenMapper tokenMapper;
	
	@Override /* 토큰 조회 */
	public boolean checkToken(TokenInfoVo tokenInfoVo) throws Exception {
		int check = tokenMapper.checkToken(tokenInfoVo);
		
		if(check == 1)
			return true;
		return false;
	}

	@Override /* 토큰 삭제 */
	public void removeToken(String userEmail) throws Exception {
		
		tokenMapper.removeToken(userEmail);
	}

}
