package com.traveler.main.vo.trip;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Alias("TripKategorieVo")
@AllArgsConstructor
@NoArgsConstructor
public class TripKategorieVo {
	private PagingVo pagingVo;
	private String kategorie;
	
}
