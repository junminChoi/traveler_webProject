package com.traveler.main.controller.weather;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.service.weather.WeatherService;
import com.traveler.main.vo.reponse.ResDataVo;
import com.traveler.main.vo.weather.WeatherVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {
	
	private final WeatherService weatherService;
		
	/* 중기전망 조회 */
	@GetMapping("/midfcst")
	public ResponseEntity<ResDataVo> getMidFcst(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo, 
							 					@RequestParam(value = "numOfRows", defaultValue = "10") String numOfRows, 
							 					@RequestParam(value = "dataType", defaultValue = "JSON") String dataType, 
							 					@RequestParam(value = "stnId", defaultValue = "105") String stnId, // Default = 105 (강원)
							 					@RequestParam(value = "tmFc", defaultValue = "") String tmFc) throws IOException, ParseException {
		log.info("URI = /api/weather/midfcst?pageNo={}&numOfRows={}&dataType={}&stnId={}&tmFc={}", pageNo, numOfRows, dataType, stnId, tmFc);
		
		WeatherVo weatherVo = new WeatherVo(pageNo, numOfRows, dataType, tmFc);
		weatherVo.setStnId(stnId);

		String weatherData = weatherService.getMidFcst(weatherVo);

		// JSON Parsing
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(weatherData);
		JSONObject response = (JSONObject) jsonObj.get("response");
		JSONObject body = (JSONObject) response.get("body");
		JSONObject items = (JSONObject) body.get("items");	
		JSONArray item = (JSONArray) items.get("item");
		JSONObject wfSv = (JSONObject) item.get(0);
		
		return ResponseEntity.ok().body(new ResDataVo(200, "SUCCESS", wfSv));
	}
	
	/* 중기기온 조회 */
	@GetMapping("/midta")
	public ResponseEntity<ResDataVo> getMidTa(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo, 
			   		 	   					  @RequestParam(value = "numOfRows", defaultValue = "10") String numOfRows, 
			   		 	   					  @RequestParam(value = "dataType", defaultValue = "JSON") String dataType, 
			   		 	   					  @RequestParam(value = "regId", defaultValue = "11D10301") String regId, // Default = 11D10301(춘천)
			   		 	   					  @RequestParam(value = "tmFc", defaultValue = "") String tmFc) throws IOException, ParseException {	
		log.info("URI = /api/weather/midta?pageNo={}&numOfRows={}&dataType={}&regId={}&tmFc={}", pageNo, numOfRows, dataType, regId, tmFc);
		
		WeatherVo weatherVo = new WeatherVo(pageNo, numOfRows, dataType, tmFc);
		weatherVo.setRegId(regId);
		
		String weatherData = weatherService.getMidTa(weatherVo);
		
		// JSON Parsing
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(weatherData);
		JSONObject response = (JSONObject) jsonObj.get("response");
		JSONObject body = (JSONObject) response.get("body");
		JSONObject items = (JSONObject) body.get("items");	
		
		return ResponseEntity.ok().body(new ResDataVo(200, "SUCCESS", items));
	}
	
	/* 중기육상예보 조회 */
	@GetMapping("/midlandfcst")
	public ResponseEntity<ResDataVo> getMidLandFcst(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo, 
			   		 		  						@RequestParam(value = "numOfRows", defaultValue = "10") String numOfRows, 
			   		 		  						@RequestParam(value = "dataType", defaultValue = "JSON") String dataType, 
			   		 		  						@RequestParam(value = "regId", defaultValue = "11D10000") String regId, // default = 11D10000(영서)
			   		 		  						@RequestParam(value = "tmFc", defaultValue = "") String tmFc) throws IOException, ParseException {	
		log.info("URI = /api/weather/midlandfcst?pageNo={}&numOfRows={}&dataType={}&regId={}&tmFc={}", pageNo, numOfRows, dataType, regId, tmFc);
		
		WeatherVo weatherVo = new WeatherVo(pageNo, numOfRows, dataType, tmFc);
		weatherVo.setRegId(regId);
		
		String weatherData = weatherService.getMidLandFcst(weatherVo);
		
		// JSON Parsing
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(weatherData);
		JSONObject response = (JSONObject) jsonObj.get("response");
		JSONObject body = (JSONObject) response.get("body");
		JSONObject items = (JSONObject) body.get("items");
		
		return ResponseEntity.ok().body(new ResDataVo(200, "SUCCESS", items));
	}
}
