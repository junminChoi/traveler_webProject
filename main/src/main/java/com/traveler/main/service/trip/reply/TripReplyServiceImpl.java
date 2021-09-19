package com.traveler.main.service.trip.reply;

import java.util.List;

import org.springframework.stereotype.Service;

import com.traveler.main.mapper.trip.TripReplyMapper;
import com.traveler.main.vo.trip.reply.ModifyReplyVo;
import com.traveler.main.vo.trip.reply.ReplyVo;
import com.traveler.main.vo.trip.reply.TripCommentVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripReplyServiceImpl implements TripReplyService {

	private final TripReplyMapper tripReplyMapper;
	
	@Override /* 관광지 댓글조회 */
	public List<TripCommentVo> tripReplyList(String loction) throws Exception {
		
		return tripReplyMapper.tripReplyList(loction);
	}

	@Override /* 관광지 댓글 등록 */
	public void addTripReply(ReplyVo replyVo) throws Exception {
		
		tripReplyMapper.addTripReply(replyVo);
		tripReplyMapper.addReplyCount(replyVo.getLocation());
	}

	@Override /* 조건에 맞는 댓글 조회 */
	public TripCommentVo replyInfo(ModifyReplyVo modifyReplyVo) throws Exception {
		
		return tripReplyMapper.replyInfo(modifyReplyVo);
	}

	@Override /* 댓글 수정 */
	public void modifyReplyInfo(TripCommentVo tripCommentVo) throws Exception {
		
		tripReplyMapper.modifyReplyInfo(tripCommentVo);
	}

	@Override /* 댓글 삭제 */
	public void removeReply(ModifyReplyVo modifyReplyVo) throws Exception {
		
		tripReplyMapper.removeReply(modifyReplyVo);
	}

	
}
