package com.traveler.main.service.trip.reply;

import java.sql.SQLException;
import java.util.List;

import com.traveler.main.vo.trip.reply.ModifyReplyVo;
import com.traveler.main.vo.trip.reply.ReplyVo;
import com.traveler.main.vo.trip.reply.TripCommentVo;

public interface TripReplyService {

	/* 관광지 댓글조회 */
	public List<TripCommentVo> tripReplyList(String loction) throws SQLException;
	
	/* 관광지 댓글 등록 */
	public void addTripReply(ReplyVo replyVo) throws SQLException;
	
	/* 조건에 맞는 댓글 조회 */
	public TripCommentVo replyInfo(ModifyReplyVo modifyReplyVo) throws SQLException;
	
	/* 댓글 수정 */
	public void modifyReplyInfo(TripCommentVo tripCommentVo) throws SQLException;
	
	/* 댓글 삭제 */
	public void removeReply(ModifyReplyVo modifyReplyVo) throws SQLException;
}
