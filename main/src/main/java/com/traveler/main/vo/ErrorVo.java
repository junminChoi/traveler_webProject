package com.traveler.main.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorVo {
	private int status;
	private String message;
	
	public ErrorVo(int string, String message) {
		this.status = string;
		this.message = message;
	}
	
}
