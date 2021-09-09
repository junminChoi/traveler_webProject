package com.traveler.main.service.trip;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.traveler.main.mapper.trip.TripInfoMapper;
import com.traveler.main.vo.trip.PagingVo;
import com.traveler.main.vo.trip.TripDataVo;
import com.traveler.main.vo.trip.TripKategorieVo;
import com.traveler.main.vo.trip.TripListVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripInfoServiceImpl implements TripInfoService {

	private final TripInfoMapper tripInfoMapper;

	@Override /* 관광지 목록 조회 (페이징) */
	public List<TripListVo> inquireTripList(int listCount, int pageNumber) throws SQLException {
		PagingVo pagingVo = new PagingVo(listCount, pageNumber);
		
		return tripInfoMapper.inquireTripList(pagingVo);
	}

	@Override /* 관광지 상세정보 조회 */
	public TripDataVo inquireTripInfo(String location) throws SQLException {
		
		return tripInfoMapper.inquireTripInfo(location);
	}

	@Override /* 관광지 리스트 총 페이지 수 */
	public int pageTotalCount(int listCount) throws SQLException {
		
		return (int) Math.ceil(tripInfoMapper.tripTotalCount() / (double) listCount);
	}

	@Override /* 지역별 관광지 목록조회 (페이징) */
	public List<TripListVo> inquireAreaTripList(int listCount, int pageNumber, String areaName) throws SQLException {
		TripKategorieVo tripKategorieVo = new TripKategorieVo(new PagingVo(listCount, pageNumber), areaName);
		
		return tripInfoMapper.inquireAreaTripList(tripKategorieVo);
	}

	@Override /* 지역별 관광지 리스트 총 페이지 수 */
	public int areaPageTotalCount(int listCount, String areaName) throws SQLException {
		
		return (int) Math.ceil(tripInfoMapper.areaTripTotalCount(areaName) / (double) listCount);
	}
	
}
