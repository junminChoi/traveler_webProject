package com.traveler.main.controller.trip;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.service.trip.TripInfoService;
import com.traveler.main.vo.reponse.ResponseDataVo;
import com.traveler.main.vo.reponse.ResponseVo;
import com.traveler.main.vo.trip.TripDataVo;
import com.traveler.main.vo.trip.TripListVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/trip")
@RequiredArgsConstructor
public class TripInfoController {

	private final TripInfoService tripInfoService;
	
	@GetMapping("/list") /* 관광지 목로조회 (페이징) */
	public ResponseEntity<Object> inquireTripList(@RequestParam(value = "cnt", defaultValue = "10") int listCount,
												  @RequestParam(value = "num", defaultValue = "1") int pageNumber,
												  @RequestParam(value = "area", required = false, defaultValue = " ") String areaName) throws SQLException {
		log.info("[Controller] [inquireTripList] uri= /api/trip/list?cnt={}&num={}&area={}", listCount, pageNumber, areaName);
		
		List<TripListVo> tripListVo;
		if(!areaName.equals(" "))
			tripListVo = tripInfoService.inquireAreaTripList(listCount, pageNumber, areaName);
		else
			tripListVo = tripInfoService.inquireTripList(listCount, pageNumber);
		
		return ResponseEntity.ok().body(new ResponseDataVo(200, "SUCCESS", tripListVo));
	}
	
	@GetMapping("/info") /* 관광지 상세정보 조회 */
	public ResponseEntity<Object> inquireTripInfo(@RequestParam(value="loc") String location) throws SQLException {
		log.info("[Controller] [inquireTripInfo] uri= /api/trip/info?loc={}", location);
		
		TripDataVo tripDataVo = tripInfoService.inquireTripInfo(location);
		
		if(Objects.isNull(tripDataVo))
			return ResponseEntity.ok().body(new ResponseVo(400, "관광지 정보가 존재하지 않습니다."));
		return ResponseEntity.ok().body(new ResponseDataVo(200, "SUCCESS", tripDataVo));
	}
	
	@GetMapping("/page/total") /* 관광지 리스트 총 페이지 개수 */
	public ResponseEntity<ResponseDataVo> pageTotalCount(@RequestParam(value = "cnt") int listCount,
														 @RequestParam(value = "area", required = false, defaultValue = " ") String areaName) throws SQLException {
		log.info("[Controller] [pageTotalCount] uri= /api/trip/page/total?cnt={}&area={}", listCount, areaName);
		
		int totalPage = -1;
		if(!areaName.equals(" "))
			totalPage = tripInfoService.areaPageTotalCount(listCount, areaName);
		else
			totalPage = tripInfoService.pageTotalCount(listCount);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("totalPage", totalPage);
		
		return ResponseEntity.ok().body(new ResponseDataVo(200, "SUCCESS", map));
	}
}
