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

	private String userNickName;
	
	@NotNull
	private String tempTime;
	
	private String content;
	
	@NotNull /* Primary Key */
	private int commentID;

	public TripCommentVo(String location, String content, @NotNull int commentID) {
		this.location = location;
		this.content = content;
		this.commentID = commentID;
		this.userNickName = "";
		this.tempTime = "";
	}
}
