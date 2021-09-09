package com.traveler.main.service.trip.reply;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.traveler.main.mapper.trip.TripReplyMapper;
import com.traveler.main.vo.trip.reply.ReplyVo;
import com.traveler.main.vo.trip.reply.TripCommentVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripReplyServiceImpl implements TripReplyService {

	private final TripReplyMapper tripReplyMapper;
	
	@Override /* 관광지 댓글조회 */
	public List<TripCommentVo> tripReplyList(String loction) throws SQLException {
		
		return tripReplyMapper.tripReplyList(loction);
	}

	@Override /* 관광지 댓글 등록 */
	public void addTripReply(ReplyVo replyVo) throws SQLException {
		
		tripReplyMapper.addTripReply(replyVo);
	}

}
