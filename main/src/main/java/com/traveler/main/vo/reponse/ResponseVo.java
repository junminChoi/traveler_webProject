package com.traveler.main.vo.reponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseVo {
	private int status;
	private String message;
	
	public ResponseVo(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
}
