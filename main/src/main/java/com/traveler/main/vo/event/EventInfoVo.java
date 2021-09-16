package com.traveler.main.vo.event;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("EventInfoVo")
@AllArgsConstructor
public class EventInfoVo {

	@NotNull
	private int eventId;  /* PRIMARY KEY */
	private String title;
}
