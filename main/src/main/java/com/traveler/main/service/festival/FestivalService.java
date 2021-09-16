package com.traveler.main.service.festival;

import java.util.List;

import com.traveler.main.vo.festival.FestivalListVo;
import com.traveler.main.vo.festival.FestivalVo;
import com.traveler.main.vo.trip.PagingVo;

public interface FestivalService {

	/* 문화축제 리스트 조회 (페이징) */
	public List<FestivalListVo> festivalList(PagingVo pagingVo) throws Exception;

	/* 문화축제 상세정보 조회 */
	public FestivalVo festivalInfo(int id, String title) throws Exception;
	
	/* 문화축제 리스트 총 페이지 수 */
	public int festivalTotalPageCount(int listCount) throws Exception;
}
