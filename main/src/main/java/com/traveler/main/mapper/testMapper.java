package com.traveler.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.traveler.main.vo.testVo;

@Repository
@Mapper
public interface testMapper {
	
	List<testVo> listMap();

}
