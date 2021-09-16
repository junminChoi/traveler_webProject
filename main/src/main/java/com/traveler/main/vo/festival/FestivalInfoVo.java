package com.traveler.main.vo.festival;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("FestivalInfoVo")
@AllArgsConstructor
public class FestivalInfoVo {

	@NotNull
	private int festivalId;  /* PRIMARY KEY */
	private String title;
}
