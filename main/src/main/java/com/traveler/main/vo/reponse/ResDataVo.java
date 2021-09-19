package com.traveler.main.vo.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResDataVo {
	private int status;
	private String message;
	private Object response;
}
