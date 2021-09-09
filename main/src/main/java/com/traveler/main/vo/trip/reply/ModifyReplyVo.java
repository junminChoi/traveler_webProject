package com.traveler.main.vo.trip.reply;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Alias("ModifyReplyVo")
@AllArgsConstructor
@NoArgsConstructor
public class ModifyReplyVo {
	private String location;
	private String userNickName;
	
	@NotNull /* Primary Key */
	private int commentID;

	public ModifyReplyVo(String location, @NotNull int commentID) {
		this.location = location;
		this.commentID = commentID;
		this.userNickName = "";
	}
	
	
}
