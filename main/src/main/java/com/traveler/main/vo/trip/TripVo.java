package com.traveler.main.vo.trip;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("TripVo")
@AllArgsConstructor
public class TripVo {
	
	@NotNull
	private String title;		// destinationTitle
	
	private String imageUrl;	// pictureData
	
	@NotNull /* Primary Key */
	private String location;	// location
	
	private int replyCnt;		// viewCount
	private String content;		// content
	private String phoneNum;	// phoneNumber
}
