package com.traveler.main.controller.trip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.service.trip.TripInfoService;
import com.traveler.main.vo.reponse.ResDataVo;
import com.traveler.main.vo.reponse.ResVo;
import com.traveler.main.vo.trip.TripListVo;
import com.traveler.main.vo.trip.TripVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/trip")
@RequiredArgsConstructor
public class TripInfoController {

	private final TripInfoService tripInfoService;
	
	/* 관광지 리스트 조회 (페이징) */
	@GetMapping("/list") 
	public ResponseEntity<Object> inquireTripList(@RequestParam(value = "cnt", defaultValue = "10") int listCount,
										   		  @RequestParam(value = "num", defaultValue = "1") int pageNumber,
										   		  @RequestParam(value = "area", defaultValue = "") String areaName) throws Exception {
		log.info("URI = /api/trip/list?cnt={}&num={}&area={}", listCount, pageNumber, areaName);
		
		Map <String, Object> map = new HashMap<>();
		List<TripListVo> tripListVo;
		if(!areaName.isBlank())
			tripListVo = tripInfoService.inquireAreaTripList(listCount, pageNumber, areaName);
		else
			tripListVo = tripInfoService.inquireTripList(listCount, pageNumber);
		
		map.put("list", tripListVo);
		return ResponseEntity.ok().body(new ResDataVo(200, "SUCCESS", map));
	}
	
	/* 관광지 상세정보 조회 */
	@GetMapping("/info")
	public ResponseEntity<Object> inquireTripInfo(@RequestParam(value="loc", required = false) String location) throws Exception {
		log.info("URI = /api/trip/info?loc={}", location);
		
		Map <String, Object> map = new HashMap<>();
		TripVo tripDataVo = tripInfoService.inquireTripInfo(location);
		
		if(Objects.isNull(tripDataVo))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResVo(404, "Not Found"));
		
		map.put("info", tripDataVo);
		return ResponseEntity.ok().body(new ResDataVo(200, "SUCCESS", map));
	}
	
	/* 관광지 리스트 총 페이지 개수 */
	@GetMapping("/list/totalpage")
	public ResponseEntity<ResDataVo> pageTotalCount(@RequestParam(value = "cnt", defaultValue = "10") int listCount,
													@RequestParam(value = "area", defaultValue = "") String areaName) throws Exception {
		log.info("URI = /api/trip/list/totalpage?cnt={}&area={}", listCount, areaName);
		
		int totalPage = -1;
		if(!areaName.isBlank())
			totalPage = tripInfoService.areaPageTotalCount(listCount, areaName);
		else
			totalPage = tripInfoService.pageTotalCount(listCount);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("totalPage", totalPage);
		
		return ResponseEntity.ok().body(new ResDataVo(200, "SUCCESS", map));
	}
}
