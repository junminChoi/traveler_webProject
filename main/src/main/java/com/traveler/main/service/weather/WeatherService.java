package com.traveler.main.service.weather;

import java.io.IOException;

import com.traveler.main.vo.weather.WeatherVo;

public interface WeatherService {

	/* 중기전망조회 */
	public String getMidFcst(WeatherVo weatherVo) throws IOException;
	
	/* 중기기온조회 */
	public String getMidTa(WeatherVo weatherVo) throws IOException;
	
	/* 중기육상예보조회 */
	public String getMidLandFcst(WeatherVo weatherVo) throws IOException;
}
