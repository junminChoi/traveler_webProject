package com.traveler.main.vo.trip.reply;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Alias("TripCommentVo")
@AllArgsConstructor
@NoArgsConstructor
public class TripCommentVo {
	
	/* Foreign Key */
	private String location;

	private String nickName; //userNickName
	
	@NotNull
	private String dateTime; //tempTime
	
	private String content;
	
	@NotNull /* Primary Key */
	private int replyId; // commentID

	public TripCommentVo(String location, String content, @NotNull int replyId) {
		this.location = location;
		this.content = content;
		this.replyId = replyId;
		this.nickName = "";
		this.dateTime = "";
	}
}
