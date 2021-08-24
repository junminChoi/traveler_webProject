package com.traveler.main.vo.token;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Alias("TokenInfoVo")
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfoVo {
	private String userEmail;
	private String token;
}
