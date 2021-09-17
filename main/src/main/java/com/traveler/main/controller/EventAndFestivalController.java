package com.traveler.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.service.event.EventService;
import com.traveler.main.service.festival.FestivalService;
import com.traveler.main.vo.event.EventListVo;
import com.traveler.main.vo.event.EventVo;
import com.traveler.main.vo.festival.FestivalListVo;
import com.traveler.main.vo.festival.FestivalVo;
import com.traveler.main.vo.reponse.ResponseDataVo;
import com.traveler.main.vo.reponse.ResponseVo;
import com.traveler.main.vo.trip.PagingVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventAndFestivalController {
	
	private final EventService eventService;
	private final FestivalService festivalService;
	
	private boolean nullCheckObject(Object obj) {
		if(Objects.isNull(obj)) // null
			return true;
		return false;
	}
	
	/* 문화축제, 공연행사 리스트 조회 (페이징) */
	@GetMapping("/{type}/list")  // type = festival(문화축제), event(공연행사)
	public ResponseEntity<Object> list(@PathVariable("type") String type,
									   @RequestParam(value = "cnt", defaultValue = "10") int listCount,
									   @RequestParam(value = "num", defaultValue = "1") int pageNumber) throws Exception {
		log.info("URI = /api/{}/list?cnt={}&num={}", type, listCount, pageNumber);
		
		PagingVo pagingVo = new PagingVo(listCount, pageNumber);
		
		if(type.equals("event")) { // 공연행사
			List<EventListVo> eventListVo = eventService.eventList(pagingVo);
			return ResponseEntity.ok().body(new ResponseDataVo(200, "SUCCESS", eventListVo));
		}
		
		if(type.equals("festival")) { // 문화축제
			List<FestivalListVo> festivalListVo = festivalService.festivalList(pagingVo);
			return ResponseEntity.ok().body(new ResponseDataVo(200, "SUCCESS", festivalListVo));
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseVo(404, "FAIL"));
	}
	
	/* 문화축제, 공연행사 상세정보 조회 */
	@GetMapping("/{type}/info")
	public ResponseEntity<Object> info(@PathVariable("type") String type,
									   @RequestParam(value = "id", required = false) int id,
									   @RequestParam(value = "title", required = false) String title) throws Exception {
		log.info("URI = /api/{}/info?id={}&title={}", type, id, title);
		
		if(type.equals("event")) { // 공연행사
			EventVo eventVo = eventService.eventInfo(id, title);
			if(!nullCheckObject(eventVo))
				return ResponseEntity.ok().body(new ResponseDataVo(200, "SUCCESS", eventVo));
		}
		
		if(type.equals("festival")) { // 문화축제
			FestivalVo festivalVo = festivalService.festivalInfo(id, title);
			if(!nullCheckObject(festivalVo))
				return ResponseEntity.ok().body(new ResponseDataVo(200, "SUCCESS", festivalVo));
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseVo(404, "FAIL"));
	}
	
	/* 문화축제, 공연행사 리스트 총 페이지 수 */
	@GetMapping("/{type}/list/totalpage")
	public ResponseEntity<ResponseDataVo> totalPageCount(@PathVariable("type") String type,
												 		 @RequestParam(value = "cnt", defaultValue = "10") int listCount) throws Exception {
		log.info("URI = /api/{}/list/totalpage?cnt={}", type, listCount);
		
		int pageCount = 0;
		if(type.equals("event")) // 공연행사
			pageCount = eventService.eventTotalPageCount(listCount);
		if(type.equals("festival")) // 문화축제
			pageCount = festivalService.festivalTotalPageCount(listCount);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("count", pageCount);
		
		return ResponseEntity.ok().body(new ResponseDataVo(200, "SUCCESS", map));
	}
}
