package com.traveler.main.vo.trip.reply;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Alias("ReplyVo")
@NoArgsConstructor
public class ReplyVo {
	
	/* Foreign Key */
	private String location;
	
	private String nickName; // userNickName
	private String content;
	
	public ReplyVo(String location, String content) {
		this.location = location;
		this.content = content;
		this.nickName = "";
	}
}
