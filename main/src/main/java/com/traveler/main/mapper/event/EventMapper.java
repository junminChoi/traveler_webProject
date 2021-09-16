package com.traveler.main.mapper.event;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.traveler.main.vo.event.EventInfoVo;
import com.traveler.main.vo.event.EventListVo;
import com.traveler.main.vo.event.EventVo;
import com.traveler.main.vo.trip.PagingVo;

@Mapper
@Repository
public interface EventMapper {
	
	/* 공연행사 리스트 조회 (페이징) */
	public List<EventListVo> eventList(PagingVo pagingVo) throws Exception;
	
	/* 공연행사 상세정보 조회 */
	public EventVo eventInfo(EventInfoVo eventInfoVo) throws Exception;
	
	/* 공연행사 리스트 개수 */
	public int eventTotalCount() throws Exception;
}
