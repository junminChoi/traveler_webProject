package com.traveler.main.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.traveler.main.vo.ErrorVo;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

	@ExceptionHandler /* @RquestBody @Validated 예외처리 */
	public ResponseEntity<ErrorVo> MethodArgumentNotValidExHandle (MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		String message = bindingResult.getFieldError().getDefaultMessage();
		
		return ResponseEntity.ok().body(new ErrorVo(400, message));
	}
	
	@ExceptionHandler /* @PathVariable @Validated 예외처리 */
	public ResponseEntity<ErrorVo> ConstraintViolationExHandle(ConstraintViolationException e) {
		return ResponseEntity.ok().body(new ErrorVo(400, e.getMessage()));
	}
	
	@ExceptionHandler /* 유효하지 않은 토큰 예외처리 */
	public ResponseEntity<ErrorVo> JwtExHandle(JwtException e) {
		return ResponseEntity.ok().body(new ErrorVo(400, e.getMessage()));
	}
	
	@ExceptionHandler /* 서버 예외처리 */
	public ResponseEntity<ErrorVo> exHandle(Exception e) {
		log.error("[Exception] ClassName = {}, Message = {}", e.getClass().getSimpleName(), e.getMessage());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorVo(500, "Server Error"));
	}
	
}
