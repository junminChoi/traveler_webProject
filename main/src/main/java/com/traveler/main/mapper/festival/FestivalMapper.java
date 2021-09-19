package com.traveler.main.mapper.festival;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.traveler.main.vo.festival.FestivalInfoVo;
import com.traveler.main.vo.festival.FestivalListVo;
import com.traveler.main.vo.festival.FestivalVo;
import com.traveler.main.vo.paging.PagingVo;

@Mapper
@Repository
public interface FestivalMapper {

	/* 문화축제 리스트 조회 (페이징) */
	public List<FestivalListVo> festivalList(PagingVo pagingVo) throws Exception;
	
	/* 문화축제 상세정보 조회 */
	public FestivalVo festivalInfo(FestivalInfoVo festivalInfoVo) throws Exception;

	/* 문화축제 리스트 개수 */
	public int festivalTotalCount() throws Exception;
}
