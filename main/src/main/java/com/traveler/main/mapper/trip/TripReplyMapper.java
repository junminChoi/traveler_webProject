package com.traveler.main.mapper.trip;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.traveler.main.vo.trip.reply.ReplyVo;
import com.traveler.main.vo.trip.reply.TripCommentVo;

@Mapper
@Repository
public interface TripReplyMapper {

	/* 관광지 댓글 조회 */
	public List<TripCommentVo> tripReplyList(String location) throws SQLException;
	
	/* 관광지 댓글 등록 */
	public void addTripReply(ReplyVo replyVo) throws SQLException;
}
