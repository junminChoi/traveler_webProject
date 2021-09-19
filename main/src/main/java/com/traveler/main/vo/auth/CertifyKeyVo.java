package com.traveler.main.vo.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("CertifyKeyVo")
@AllArgsConstructor
public class CertifyKeyVo {
	
	@NotBlank
	private String mail; // userEmail

	@NotNull
	private Integer certifyKey;
	
}
