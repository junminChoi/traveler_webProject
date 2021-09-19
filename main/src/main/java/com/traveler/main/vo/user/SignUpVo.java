package com.traveler.main.vo.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpVo {
	
	@NotBlank
	@Email
	private String mail; //userEmail;
	
	@NotNull
	private Integer certifyKey;
	
	@NotBlank
	@Length(min = 2, max = 10)
	private String nickName; //userNickName;
	
	@NotBlank
	@Length(min = 8, max = 20)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?])[A-Za-z[0-9]$@$!%*#?&]{8,20}$", message = "비밀번호 형식에 맞지 않습니다.")
	private String passwd; //userPW;
	
	@NotBlank
	@Length(min = 8, max = 20)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?])[A-Za-z[0-9]$@$!%*#?&]{8,20}$", message = "비밀번호 형식에 맞지 않습니다.")
	private String passwdChk; //userPWchk;
	
}
