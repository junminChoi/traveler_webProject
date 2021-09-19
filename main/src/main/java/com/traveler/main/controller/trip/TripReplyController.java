package com.traveler.main.controller.trip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveler.main.service.trip.reply.TripReplyService;
import com.traveler.main.vo.reponse.ResDataVo;
import com.traveler.main.vo.reponse.ResVo;
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
	public ResponseEntity<Object> tripReplyList(@RequestParam(value = "loc", required = false) String location) throws Exception {
		log.info("URI = /api/trip/reply/list?loc={}", location);
		
		Map<String, Object> map = new HashMap<>();
		List<TripCommentVo> tripCommentVo = tripReplyService.tripReplyList(location);
		if(Objects.isNull(tripCommentVo))
			return ResponseEntity.ok().body(new ResVo(200, "등록된 댓글이 없습니다."));
		
		map.put("list", tripCommentVo);
		return ResponseEntity.ok().body(new ResDataVo(200, "SUCCESS", map));
	}
	
	@PostMapping("/add") /* 댓글 등록 */ /* 토근 인증 */
	public ResponseEntity<ResVo> addTripReply(HttpServletRequest request, @RequestBody ReplyVo replyVo) throws Exception{
		log.info("URI = /api/trip/reply/add");
		
		String userNickName = (String) request.getAttribute("userNickName");
		replyVo.setNickName(userNickName);
		tripReplyService.addTripReply(replyVo);
		
		return ResponseEntity.ok().body(new ResVo(200, "SUCCESS"));
	}
	
	@GetMapping("/modify") /* 댓글 수정 (댓글 정보 불러오기) */ /* 토근 인증 */
	public ResponseEntity<Object> replyInfo(HttpServletRequest request,
											@RequestParam(value = "id", required = false) int replyId,
											@RequestParam(value = "loc", required = false) String location) throws Exception {
		log.info("URI = /api/trip/modify?id={}&loc={}", replyId, location);
		
		String userNickName = (String) request.getAttribute("userNickName");
		TripCommentVo tripCommentVo = tripReplyService.replyInfo(new ModifyReplyVo(location, userNickName, replyId));
		Map <String, Object> map = new HashMap<>();
		
		if(Objects.isNull(tripCommentVo))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResVo(400, "댓글이 존재하지 않습니다."));
		
		map.put("info", tripCommentVo);
		return ResponseEntity.ok().body(new ResDataVo(200, "SUCCESS", map));
	}
	
	@PostMapping("/modify") /* 댓글 수정 (댓글 정보 업데이트) */ /* 토근 인증 */
	public ResponseEntity<ResVo> modifyReplyInfo(HttpServletRequest request, @RequestBody TripCommentVo tripCommentVo) throws Exception{
		log.info("URI = /api/trip/modify");
		
		String userNickName = (String) request.getAttribute("userNickName");
		tripCommentVo.setNickName(userNickName);
		tripReplyService.modifyReplyInfo(tripCommentVo);
		
		return ResponseEntity.ok().body(new ResVo(200, "SUCCESS"));
	}
	
	@PostMapping("/remove") /* 댓글 삭제 */ /* 토근 인증 */
	public ResponseEntity<ResVo> removeReply(HttpServletRequest request, @RequestBody ModifyReplyVo modifyReplyVo) throws Exception {
		log.info("URI = /api/trip/remove");
		
		String userNickName = (String) request.getAttribute("userNickName");
		modifyReplyVo.setNickName(userNickName);
		tripReplyService.removeReply(modifyReplyVo);
		
		return ResponseEntity.ok().body(new ResVo(200, "SUCCESS"));
	}
}
