package com.traveler.main.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traveler.main.service.TestService;
import com.traveler.main.vo.testVo;

@Controller
public class homeController {

	@Autowired
	TestService testService;

	@GetMapping(value = "/test")
	public void testmvc(){
		
		List<testVo> testList = testService.selectTest();

		System.out.println(testList.toString());

	}

}
