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
	private String nickName; // userNickName
	
	@NotNull /* Primary Key */
	private int replyId; //commentID;

	public ModifyReplyVo(String location, @NotNull int replyId) {
		this.location = location;
		this.replyId = replyId;
		this.nickName = "";
	}
	
	
}
