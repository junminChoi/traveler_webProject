package com.traveler.main.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.traveler.main.vo.user.SignInVo;

@Mapper
@Repository
public interface UserDetailMapper {
	
	public int checkMail(String userEmail) throws Exception;
	
	public int checkNickName(String userNickName) throws Exception;
	
	public void modifyPassword(SignInVo user) throws Exception;
	
}
