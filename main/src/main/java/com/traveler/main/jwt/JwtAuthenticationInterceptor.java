package com.traveler.main.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.traveler.main.vo.token.UserTokenVo;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

	private final JwtTokenProvider jwtTokenProvider;
	
	@Value("${jwt.type}")
	private String TOKEN_TYPE;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("Interceptor URI = {}", ((HttpServletRequest)request).getRequestURI());
		
		String token = jwtTokenProvider.resolveToken(request, TOKEN_TYPE);
		
		if(token != null && jwtTokenProvider.validateToken(token)) { // 토큰이 유효한 경우
			UserTokenVo user = jwtTokenProvider.getUserTokenInfo(token);
			
			request.setAttribute("userEmail", user.getUserEmail());
			request.setAttribute("userNickName", user.getUserNickName());
			
			return true;
		}
		throw new JwtException("유효하지 않은 토큰");
	}

}
