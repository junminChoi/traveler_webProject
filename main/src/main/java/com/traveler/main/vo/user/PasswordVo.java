package com.traveler.main.vo.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PasswordVo {

	@NotBlank
	@Length(min = 8, max = 20)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?])[A-Za-z[0-9]$@$!%*#?&]{8,20}$", message = "비밀번호 형식에 맞지 않습니다.")
	private String userPW;
	
	@NotBlank
	@Length(min = 8, max = 20)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?])[A-Za-z[0-9]$@$!%*#?&]{8,20}$", message = "비밀번호 형식에 맞지 않습니다.")
	private String userPWchk;
}
