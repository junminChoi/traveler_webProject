package com.traveler.main.service.trip;

import java.sql.SQLException;
import java.util.List;

import com.traveler.main.vo.trip.TripDataVo;
import com.traveler.main.vo.trip.TripListVo;

public interface TripInfoService {

	/* 관광지 목록 조회 (페이징) */
	public List<TripListVo> inquireTripList(int listCount, int pageNumber) throws SQLException;
	
	/* 관광지 상세정보 조회 */
	public TripDataVo inquireTripInfo(String location) throws SQLException;
	
	/* 관광지 리스트 총 페이지 수 */
	public int pageTotalCount(int listCount) throws SQLException;
	
	/* 지역별 관광지 목록조회 (페이징) */
	public List<TripListVo> inquireAreaTripList(int listCount, int pageNumber, String areaName) throws SQLException;
	
	/* 지역별 관광지 리스트 총 페이지 수 */
	public int areaPageTotalCount(int listCount, String areaName) throws SQLException;
}
