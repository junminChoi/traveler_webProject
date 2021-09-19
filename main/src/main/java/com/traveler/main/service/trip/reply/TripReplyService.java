package com.traveler.main.service.trip.reply;

import java.util.List;

import com.traveler.main.vo.trip.reply.ModifyReplyVo;
import com.traveler.main.vo.trip.reply.ReplyVo;
import com.traveler.main.vo.trip.reply.TripCommentVo;

public interface TripReplyService {

	/* 관광지 댓글조회 */
	public List<TripCommentVo> tripReplyList(String loction) throws Exception;
	
	/* 관광지 댓글 등록 */
	public void addTripReply(ReplyVo replyVo) throws Exception;
	
	/* 조건에 맞는 댓글 조회 */
	public TripCommentVo replyInfo(ModifyReplyVo modifyReplyVo) throws Exception;
	
	/* 댓글 수정 */
	public void modifyReplyInfo(TripCommentVo tripCommentVo) throws Exception;
	
	/* 댓글 삭제 */
	public void removeReply(ModifyReplyVo modifyReplyVo) throws Exception;
}
