package com.traveler.main.controller.trip;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.service.trip.reply.TripReplyService;
import com.traveler.main.vo.reponse.ResponseDataVo;
import com.traveler.main.vo.reponse.ResponseVo;
import com.traveler.main.vo.trip.reply.ModifyReplyVo;
import com.traveler.main.vo.trip.reply.ReplyVo;
import com.traveler.main.vo.trip.reply.TripCommentVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trip/reply")
public class TripReplyController {

	private final TripReplyService tripReplyService;
	
	@GetMapping("/list") /* 관광지 댓글조회 */
	public ResponseEntity<Object> tripReplyList(@RequestParam(value = "loc") String location) throws SQLException {
		log.info("[Controller] [tripReplyList] uri = /api/trip/reply/list?loc={}", location);
		
		List<TripCommentVo> tripCommentVo = tripReplyService.tripReplyList(location);
		if(Objects.isNull(tripCommentVo))
			return ResponseEntity.ok().body(new ResponseVo(200, "댓글 없음"));
		return ResponseEntity.ok().body(new ResponseDataVo(200, "SUCCESS", tripCommentVo));
	}
	
	@PostMapping("/add") /* 댓글 등록 */ /* 토근 인증 */
	public ResponseEntity<ResponseVo> addTripReply(HttpServletRequest request, @RequestBody ReplyVo replyVo) throws SQLException{
		log.info("[Controller] [addTripReply] uri = /api/trip/reply/add");
		
		String userNickName = (String) request.getAttribute("userNickName");
		replyVo.setUserNickName(userNickName);
		tripReplyService.addTripReply(replyVo);
		
		return ResponseEntity.ok().body(new ResponseVo(200, "SUCCESS"));
	}
	
	@GetMapping("/modify") /* 댓글 수정 (댓글 정보 불러오기) */ /* 토근 인증 */
	public ResponseEntity<Object> replyInfo(HttpServletRequest request,
													@RequestParam(value = "id") int replyId,
													@RequestParam(value = "loc") String location) throws SQLException {
		log.info("[Controller] [replyInfo] uri = /api/trip/modify?id={}&loc={}", replyId, location);
		String userNickName = (String) request.getAttribute("userNickName");
		
		TripCommentVo tripCommentVo = tripReplyService.replyInfo(new ModifyReplyVo(location, userNickName, replyId));
		if(Objects.isNull(tripCommentVo))
			return ResponseEntity.ok().body(new ResponseVo(200, "댓글이 없습니다."));
		return ResponseEntity.ok().body(new ResponseDataVo(200, "SUCCESS", tripCommentVo));
	}
	
	@PostMapping("/modify") /* 댓글 수정 (댓글 정보 업데이트) */ /* 토근 인증 */
	public ResponseEntity<ResponseVo> modifyReplyInfo(HttpServletRequest request, @RequestBody TripCommentVo tripCommentVo) throws SQLException{
		log.info("[Controller] [replyInfo] uri = /api/trip/modify");
		
		String userNickName = (String) request.getAttribute("userNickName");
		tripCommentVo.setUserNickName(userNickName);
		tripReplyService.modifyReplyInfo(tripCommentVo);
		
		return ResponseEntity.ok().body(new ResponseVo(200, "SUCCESS"));
	}
	
	@PostMapping("/remove") /* 댓글 삭제 */ /* 토근 인증 */
	public ResponseEntity<ResponseVo> removeReply(HttpServletRequest request, @RequestBody ModifyReplyVo modifyReplyVo) throws SQLException {
		log.info("[Controller] [replyInfo] uri = /api/trip/remove");
		
		String userNickName = (String) request.getAttribute("userNickName");
		modifyReplyVo.setUserNickName(userNickName);
		tripReplyService.removeReply(modifyReplyVo);
		
		return ResponseEntity.ok().body(new ResponseVo(200, "SUCCESS"));
	}
}
