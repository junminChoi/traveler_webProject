package com.traveler.main.mapper.tripInfoMapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.traveler.main.vo.tripInfoVO.tripInfoVO;

@Repository
@Mapper
public interface tripInfoMapper {
	List<tripInfoVO> getInformation();
}
