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
	private String mail; //userEmail;
	
	@NotBlank
	private String passwd; //userPW;

	public SignInVo() {}
	
	public SignInVo(@NotBlank String mail, @NotBlank String passwd) {
		this.mail = mail;
		this.passwd = passwd;
	}
	
}
