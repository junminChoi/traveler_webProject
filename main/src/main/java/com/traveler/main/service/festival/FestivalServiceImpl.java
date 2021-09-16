package com.traveler.main.service.festival;

import java.util.List;

import org.springframework.stereotype.Service;

import com.traveler.main.mapper.festival.FestivalMapper;
import com.traveler.main.vo.festival.FestivalInfoVo;
import com.traveler.main.vo.festival.FestivalListVo;
import com.traveler.main.vo.festival.FestivalVo;
import com.traveler.main.vo.trip.PagingVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService {
	
	private final FestivalMapper festivalMapper;
	
	/* 문화축제 리스트 조회 (페이징) */
	@Override
	public List<FestivalListVo> festivalList(PagingVo pagingVo) throws Exception {
		
		return festivalMapper.festivalList(pagingVo);
	}

	/* 문화축제 상세정보 조회 */
	@Override
	public FestivalVo festivalInfo(int id, String title) throws Exception {
		
		FestivalInfoVo festivalInfoVo = new FestivalInfoVo(id, title);
		return festivalMapper.festivalInfo(festivalInfoVo);
	}

	/* 문화축제 리스트 총 페이지 수 */
	@Override
	public int festivalTotalPageCount(int listCount) throws Exception {
		
		return (int) Math.ceil(festivalMapper.festivalTotalCount() / (double) listCount);
	}

}
