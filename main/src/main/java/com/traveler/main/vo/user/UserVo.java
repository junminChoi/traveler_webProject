package com.traveler.main.vo.user;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Alias("UserVo")
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
	private String userEmail;
	private String userNickName;
	private String userPW;
	
}
