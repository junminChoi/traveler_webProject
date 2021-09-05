package com.traveler.main.service.tripInfoService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traveler.main.mapper.tripInfoMapper.tripInfoMapper;
import com.traveler.main.vo.tripInfoVO.tripInfoVO;
import com.traveler.main.vo.tripInfoVO.tripInfo_contentVO;

@Service
public class tripInfoService {
	@Autowired
	public tripInfoMapper mapper;
	
	public List<tripInfoVO> getInfoData(){
		return mapper.getInformation();
	}
	
	public List<tripInfo_contentVO> getInfo_contentData(){
		return mapper.getInformation_content();
	}
}
