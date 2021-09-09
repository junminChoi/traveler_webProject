package com.traveler.main.service.trip.reply;

import java.sql.SQLException;
import java.util.List;

import com.traveler.main.vo.trip.reply.ReplyVo;
import com.traveler.main.vo.trip.reply.TripCommentVo;

public interface TripReplyService {

	/* 관광지 댓글조회 */
	public List<TripCommentVo> tripReplyList(String loction) throws SQLException;
	
	/* 관광지 댓글 등록 */
	public void addTripReply(ReplyVo replyVo) throws SQLException;
}
