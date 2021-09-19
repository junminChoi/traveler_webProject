package com.traveler.main.mapper.trip;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.traveler.main.vo.paging.PagingVo;
import com.traveler.main.vo.trip.TripKategorieVo;
import com.traveler.main.vo.trip.TripListVo;
import com.traveler.main.vo.trip.TripVo;

@Mapper
public interface TripInfoMapper {

	/* 관광자 리스트 조회 (페이징) */
	public List<TripListVo> inquireTripList(PagingVo pagingVo) throws Exception;
	
	/* 관광지 상세정보 조회 */
	public TripVo inquireTripInfo(String location) throws Exception;
	
	/* 관광지 총 개수 */
	public int tripTotalCount() throws Exception;
	
	/* 지역별 관광지 리스트 조회 (페이징) */
	public List<TripListVo> inquireAreaTripList(TripKategorieVo tripKategorieVo) throws Exception;
	
	/* 지역별 관광지 총 개수 */
	public int areaTripTotalCount(String areaName) throws Exception;
}
