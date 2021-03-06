package com.traveler.main.mapper.trip;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.traveler.main.vo.trip.reply.ModifyReplyVo;
import com.traveler.main.vo.trip.reply.ReplyVo;
import com.traveler.main.vo.trip.reply.TripCommentVo;

@Mapper
@Repository
public interface TripReplyMapper {

	/* 관광지 댓글 조회 */
	public List<TripCommentVo> tripReplyList(String location) throws Exception;
	
	/* 관광지 댓글 등록 */
	public void addTripReply(ReplyVo replyVo) throws Exception;
	
	/* 댓글 개수 증가 */
	public void addReplyCount(String location) throws Exception;
	
	/* 댓글 개수 감소 */
	public void removeReplyCount(String location) throws Exception;
	
	/* 조건에 맞는 댓글 조회 */
	public TripCommentVo replyInfo(ModifyReplyVo modifyReplyVo) throws Exception;
	
	/* 댓글 수정 */
	public void modifyReplyInfo(TripCommentVo tripCommentVo) throws Exception;
	
	/* 댓글 삭제 */
	public void removeReply(ModifyReplyVo modifyReplyVo) throws Exception;
}
