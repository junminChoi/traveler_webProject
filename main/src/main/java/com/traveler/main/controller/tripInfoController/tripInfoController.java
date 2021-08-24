package com.traveler.main.controller.tripInfoController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.service.tripInfoService.tripInfoService;
import com.traveler.main.vo.tripInfoVO.tripInfoVO;

@RestController
public class tripInfoController {
	@Autowired
	tripInfoService service;
	
	@GetMapping("/test_tripinfo")
	public List<tripInfoVO> returnTripInfo(){
		List<tripInfoVO> information = service.getInfoData();
		System.out.println(information.get(0).getTitle());
		
		
		
		return information;
	}
}
