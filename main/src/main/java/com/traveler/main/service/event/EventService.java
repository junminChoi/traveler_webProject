package com.traveler.main.service.event;

import java.util.List;

import com.traveler.main.vo.event.EventListVo;
import com.traveler.main.vo.event.EventVo;
import com.traveler.main.vo.paging.PagingVo;

public interface EventService {

	/* 공연행사 리스트 조회 (페이징) */
	public List<EventListVo> eventList(PagingVo pagingVo) throws Exception;
	
	/* 공연행사 상세정보 조회 */
	public EventVo eventInfo(int id, String title) throws Exception;
	
	/* 공연행사 리스트 총 페이지 수 */
	public int eventTotalPageCount(int listCount) throws Exception;
}
