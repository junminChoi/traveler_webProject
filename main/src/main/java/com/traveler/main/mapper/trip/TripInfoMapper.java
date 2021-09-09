package com.traveler.main.mapper.trip;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.traveler.main.vo.trip.PagingVo;
import com.traveler.main.vo.trip.TripDataVo;
import com.traveler.main.vo.trip.TripKategorieVo;
import com.traveler.main.vo.trip.TripListVo;

@Mapper
public interface TripInfoMapper {

	/* 관광자 목록조회 (페이징) */
	public List<TripListVo> inquireTripList(PagingVo pagingVo) throws SQLException;
	
	/* 관광지 상세정보 조회 */
	public TripDataVo inquireTripInfo(String location) throws SQLException;
	
	/* 관광지 총 개수 */
	public int tripTotalCount() throws SQLException;
	
	/* 지역별 관광지 목록조회 (페이징) */
	public List<TripListVo> inquireAreaTripList(TripKategorieVo tripKategorieVo) throws SQLException;
	
	/* 지역별 관광지 총 개수 */
	public int areaTripTotalCount(String areaName) throws SQLException;
}
