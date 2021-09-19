package com.traveler.main.vo.paging;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("PagingVo")
public class PagingVo {
	private int listCount;
	private int pageNumber;
	private int startPage;
	
	public PagingVo(int listCount, int pageNumber) {
		this.listCount = listCount;
		this.pageNumber = pageNumber;
		this.startPage = (pageNumber * listCount) - (listCount - 1) - 1;
	}	
}
