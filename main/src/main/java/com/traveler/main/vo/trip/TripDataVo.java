package com.traveler.main.vo.trip;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Alias("TripDataVo")
@AllArgsConstructor
@NoArgsConstructor
public class TripDataVo {
	
	@NotNull
	private String destinationTitle;
	
	private String pictureData;
	
	@NotNull /* Primary Key */
	private String location;
	
	private int viewCount;
	private String content;
	private String phoneNumber;
}
