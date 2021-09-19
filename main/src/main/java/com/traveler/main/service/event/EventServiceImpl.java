package com.traveler.main.service.event;

import java.util.List;

import org.springframework.stereotype.Service;

import com.traveler.main.mapper.event.EventMapper;
import com.traveler.main.vo.event.EventInfoVo;
import com.traveler.main.vo.event.EventListVo;
import com.traveler.main.vo.event.EventVo;
import com.traveler.main.vo.paging.PagingVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
	
	private final EventMapper eventMapper;
	
	/* 공연행사 리스트 조회 (페이징) */
	@Override
	public List<EventListVo> eventList(PagingVo pagingVo) throws Exception {
		
		return eventMapper.eventList(pagingVo);
	}

	/* 공연행사 상세정보 조회 */
	@Override
	public EventVo eventInfo(int id, String title) throws Exception {
		
		EventInfoVo eventInfoVo = new EventInfoVo(id, title);
		return eventMapper.eventInfo(eventInfoVo);
	}

	/* 공연행사 리스트 총 페이지 수 */
	@Override
	public int eventTotalPageCount(int listCount) throws Exception {
		
		return (int) Math.ceil(eventMapper.eventTotalCount() / (double) listCount);
	}

}
