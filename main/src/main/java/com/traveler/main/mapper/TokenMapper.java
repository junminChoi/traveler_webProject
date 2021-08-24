package com.traveler.main.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.traveler.main.vo.token.TokenInfoVo;

@Mapper
@Repository
public interface TokenMapper {

	public void saveToken(TokenInfoVo tokenInfoVo) throws Exception;
	
	public void removeToken(String userEmail) throws Exception;
	
	public int checkToken(TokenInfoVo tokenInfoVo) throws Exception;
	
}
