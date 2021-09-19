package com.traveler.main.vo.weather;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("WeatherVo")
public class WeatherVo {
	private String pageNo;
	private String numOfRows;
	private String dataType;
	private String tmFc;
	private String stnId;
	private String regId;
	
	public WeatherVo(String pageNo, String numOfRows, String dataType, String tmFc) {
		this.pageNo = pageNo;
		this.numOfRows = numOfRows;
		this.dataType = dataType;
		this.tmFc = tmFc;
		this.stnId = "";
		this.regId = "";
	}
}
