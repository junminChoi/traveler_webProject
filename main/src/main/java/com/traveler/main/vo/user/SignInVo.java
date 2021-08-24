package com.traveler.main.vo.user;

import javax.validation.constraints.NotBlank;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("SignInVo")
public class SignInVo {
	
	@NotBlank
	private String userEmail;
	
	@NotBlank
	private String userPW;

	public SignInVo() {}
	
	public SignInVo(@NotBlank String userEmail, @NotBlank String userPW) {
		this.userEmail = userEmail;
		this.userPW = userPW;
	}
	
}
