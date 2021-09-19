package com.traveler.main.service.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.traveler.main.vo.weather.WeatherVo;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Value("${weather.api.key}")
	private String apiKey;
	
	// 기상청 API 접근
	private String weatherAPI(StringBuilder urlBuilder, WeatherVo weatherVo) throws IOException {
		
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + apiKey);
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(weatherVo.getPageNo(), "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(weatherVo.getNumOfRows(), "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(weatherVo.getDataType(), "UTF-8"));
		if(!weatherVo.getStnId().isBlank()) {
			urlBuilder.append("&" + URLEncoder.encode("stnId", "UTF-8") + "=" + URLEncoder.encode(weatherVo.getStnId(), "UTF-8"));
		}
		if(!weatherVo.getRegId().isBlank()) {
			urlBuilder.append("&" + URLEncoder.encode("regId", "UTF-8") + "=" + URLEncoder.encode(weatherVo.getRegId(), "UTF-8"));
		}
		urlBuilder.append("&" + URLEncoder.encode("tmFc", "UTF-8") + "=" + URLEncoder.encode(weatherVo.getTmFc(), "UTF-8"));
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = rd.readLine()) != null) {
			sb.append(line);
		}
		
		rd.close();
		conn.disconnect();
		
		return sb.toString();
	}
	
	// API 자동 날짜 및 시간 계산
	private String dateTimeNow() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
		
		String dateStr = localDateTime.format(dateFormatter);
		int timeInt = Integer.parseInt(localDateTime.format(timeFormatter));
		
		if(timeInt >= 0600 && timeInt < 1800) { // 0600
			return dateStr + "0600";
		} else if(timeInt >= 1800 || timeInt < 0600 ) { // 1800
			return dateStr + "1800";
		}
		
		return "";
	}
	
	/* 중기전망조회 */
	@Override
	public String getMidFcst(WeatherVo weatherVo) throws IOException {
		
		if(weatherVo.getTmFc().isBlank()) { // 자동 날짜시간 설정
			String dateTime = dateTimeNow();
			
			if(dateTime.isBlank()) {
				throw new IOException("Auto DateTime Error");
			}
			weatherVo.setTmFc(dateTimeNow());
		}
			
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst");
		return weatherAPI(urlBuilder, weatherVo);
	}

	/* 중기기온조회 */
	@Override
	public String getMidTa(WeatherVo weatherVo) throws IOException {
		
		if(weatherVo.getTmFc().isBlank()) { // 자동 날짜시간 설정
			String dateTime = dateTimeNow();
			
			if(dateTime.isBlank()) {
				throw new IOException("Auto DateTime Error");
			}
			weatherVo.setTmFc(dateTimeNow());
		}
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa");
		return weatherAPI(urlBuilder, weatherVo);
	}

	/* 중기육상예보조회 */
	@Override
	public String getMidLandFcst(WeatherVo weatherVo) throws IOException {
		
		if(weatherVo.getTmFc().isBlank()) { // 자동 날짜시간 설정
			String dateTime = dateTimeNow();
			
			if(dateTime.isBlank()) {
				throw new IOException("Auto DateTime Error");
			}
			weatherVo.setTmFc(dateTimeNow());
		}
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst");
		return weatherAPI(urlBuilder, weatherVo);
	}
}
