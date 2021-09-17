package com.traveler.main.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.vo.weather.WeatherVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/weather")
public class WeatherController {

	@Value("${weather.api.key}")
	private String apiKey;
	
	/* 기상청 API 사용 */
	private String weather(StringBuilder urlBuilder, WeatherVo weatherVo) throws IOException {
		
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + apiKey);
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(weatherVo.getPageNo(), "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(weatherVo.getNumOfRows(), "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(weatherVo.getDataType(), "UTF-8"));
		
		if(!weatherVo.getStnId().isBlank())
			urlBuilder.append("&" + URLEncoder.encode("stnId", "UTF-8") + "=" + URLEncoder.encode(weatherVo.getStnId(), "UTF-8"));
		if(!weatherVo.getRegId().isBlank())
			urlBuilder.append("&" + URLEncoder.encode("regId", "UTF-8") + "=" + URLEncoder.encode(weatherVo.getRegId(), "UTF-8"));
		
		urlBuilder.append("&" + URLEncoder.encode("tmFc", "UTF-8") + "=" + URLEncoder.encode(weatherVo.getTmFc(), "UTF-8"));
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		//System.out.println("Response code: " + conn.getResponseCode());
		
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		else
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = rd.readLine()) != null) {
			sb.append(line);
		}
		
		rd.close();
		conn.disconnect();
		//System.out.println(sb.toString());
		
		return sb.toString();
	}
	
	/* 중기전망 조회 */
	@GetMapping("/midfcst")
	public String getMidFcst(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo, 
					   @RequestParam(value = "numOfRows", defaultValue = "10") String numOfRows, 
					   @RequestParam(value = "dataType", defaultValue = "JSON") String dataType, 
					   @RequestParam(value = "stnId", defaultValue = "105") String stnId, // default = 105(강원)
					   @RequestParam(value = "tmFc", defaultValue = "${weather.api.date}") String tmFc) throws IOException {
		log.info("URI = /api/weather/midfcst?pageNo={}&numOfRows={}&dataType={}&stnId={}&tmFc={}", pageNo, numOfRows, dataType, stnId, tmFc);
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst");
		WeatherVo weatherVo = new WeatherVo(pageNo, numOfRows, dataType, tmFc);
		weatherVo.setStnId(stnId);
		
		return weather(urlBuilder, weatherVo);
	}
	
	/* 중기기온 조회 */
	@GetMapping("/midta")
	public String getMidTa(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo, 
			   		 @RequestParam(value = "numOfRows", defaultValue = "10") String numOfRows, 
			   		 @RequestParam(value = "dataType", defaultValue = "JSON") String dataType, 
			   		 @RequestParam(value = "regId", defaultValue = "11D10301") String regId, // default = 11D10301(춘천)
			   		 @RequestParam(value = "tmFc", defaultValue = "${weather.api.date}") String tmFc) throws IOException {	
		log.info("URI = /api/weather/midta?pageNo={}&numOfRows={}&dataType={}&regId={}&tmFc={}", pageNo, numOfRows, dataType, regId, tmFc);
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa");
		WeatherVo weatherVo = new WeatherVo(pageNo, numOfRows, dataType, tmFc);
		weatherVo.setRegId(regId);
		
		return weather(urlBuilder, weatherVo);
	}
	
	/* 중기육상예보 조회 */
	@GetMapping("/midlandfcst")
	public String getMidLandFcst(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo, 
			   		 		  @RequestParam(value = "numOfRows", defaultValue = "10") String numOfRows, 
			   		 		  @RequestParam(value = "dataType", defaultValue = "JSON") String dataType, 
			   		 		  @RequestParam(value = "regId", defaultValue = "11D10000") String regId, // default = 11D10000(영서)
			   		 		  @RequestParam(value = "tmFc", defaultValue = "${weather.api.date}") String tmFc) throws IOException {	
		log.info("URI = /api/weather/midlandfcst?pageNo={}&numOfRows={}&dataType={}&regId={}&tmFc={}", pageNo, numOfRows, dataType, regId, tmFc);
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst");
		WeatherVo weatherVo = new WeatherVo(pageNo, numOfRows, dataType, tmFc);
		weatherVo.setRegId(regId);
		
		return weather(urlBuilder, weatherVo);
	}
}
