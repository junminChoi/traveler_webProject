package com.traveler.main.service.trip;

import java.util.List;

import org.springframework.stereotype.Service;

import com.traveler.main.mapper.trip.TripInfoMapper;
import com.traveler.main.vo.paging.PagingVo;
import com.traveler.main.vo.trip.TripKategorieVo;
import com.traveler.main.vo.trip.TripListVo;
import com.traveler.main.vo.trip.TripVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripInfoServiceImpl implements TripInfoService {

	private final TripInfoMapper tripInfoMapper;

	@Override /* 관광지 리스트 조회 (페이징) */
	public List<TripListVo> inquireTripList(int listCount, int pageNumber) throws Exception {
		PagingVo pagingVo = new PagingVo(listCount, pageNumber);
		
		return tripInfoMapper.inquireTripList(pagingVo);
	}

	@Override /* 관광지 상세정보 조회 */
	public TripVo inquireTripInfo(String location) throws Exception {
		
		return tripInfoMapper.inquireTripInfo(location);
	}

	@Override /* 관광지 리스트 총 페이지 수 */
	public int pageTotalCount(int listCount) throws Exception {
		
		return (int) Math.ceil(tripInfoMapper.tripTotalCount() / (double) listCount);
	}

	@Override /* 지역별 관광지 리스트 조회 (페이징) */
	public List<TripListVo> inquireAreaTripList(int listCount, int pageNumber, String areaName) throws Exception {
		TripKategorieVo tripKategorieVo = new TripKategorieVo(new PagingVo(listCount, pageNumber), areaName);
		
		return tripInfoMapper.inquireAreaTripList(tripKategorieVo);
	}

	@Override /* 지역별 관광지 리스트 총 페이지 수 */
	public int areaPageTotalCount(int listCount, String areaName) throws Exception {
		
		return (int) Math.ceil(tripInfoMapper.areaTripTotalCount(areaName) / (double) listCount);
	}
	
}
