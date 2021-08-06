package com.traveler.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traveler.main.mapper.testMapper;
import com.traveler.main.vo.testVo;

@Service
public class TestService {
	@Autowired
	public testMapper mapper;

	public List<testVo> selectTest() {
		System.out.println(mapper.listMap() + "in selectTest");
		return mapper.listMap();
	}

}
