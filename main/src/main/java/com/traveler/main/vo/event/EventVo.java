package com.traveler.main.vo.event;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("EventVo")
@AllArgsConstructor
public class EventVo {
	
	@NotNull
	private int eventId;  /* PRIMARY KEY */
	private String title;
	private String startDate;
	private String endDate;
	private String content;
	private String phoneNumber;
	private String homepage;
	private String imageUrl;
}
