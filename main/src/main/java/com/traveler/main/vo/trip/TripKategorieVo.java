package com.traveler.main.vo.trip;

import org.apache.ibatis.type.Alias;

import com.traveler.main.vo.paging.PagingVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("TripKategorieVo")
@AllArgsConstructor
public class TripKategorieVo {
	private PagingVo pagingVo;
	private String kategorie;
}
