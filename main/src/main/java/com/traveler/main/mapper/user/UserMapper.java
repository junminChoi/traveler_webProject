package com.traveler.main.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.traveler.main.vo.user.UserVo;

@Mapper
@Repository
public interface UserMapper {

	public void signUp(UserVo userVo) throws Exception;
	
	public UserVo userInfo(String userEmail) throws Exception;
	
}
