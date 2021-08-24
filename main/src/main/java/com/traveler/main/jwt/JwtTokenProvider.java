package com.traveler.main.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.traveler.main.service.token.TokenService;
import com.traveler.main.vo.token.TokenInfoVo;
import com.traveler.main.vo.token.UserTokenVo;
import com.traveler.main.vo.user.UserVo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
	
	private final TokenService tokenService;
	
	@Value("${jwt.secret-key}")
	private String secretKey;//"JwtAuthenticationTravelerSignInSecretKey";
	
	private static final long TOKEN_VALID_TIME = 30 * 60 * 1000L;
	
	@Value("${jwt.authorization}")
	private String HEADER_TOKEN_NAME;//"Authorization";
	
	/* secret key 암호화 */
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	/* 로그인 토큰 생성 */
	public String createToken(UserVo userVo) {
		Date now = new Date();
		
		return Jwts.builder()
				.setIssuer("Traveler") // 토큰 발급자
				.setIssuedAt(now) // 토큰 발행 시간
				.setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME)) // 토큰 만료 시간
				.claim("userEmail", userVo.getUserEmail())
				.claim("userNickName", userVo.getUserNickName())
				.signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘, signature에 들어갈 secret
				.compact();
	}
	
	/* 토큰에서 회원 정보 추출 */
	public UserTokenVo getUserTokenInfo(String token) {
		
		UserTokenVo user = new UserTokenVo();
		user.setUserEmail((String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userEmail"));
		user.setUserNickName((String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userNickName"));
		
		return user;
	}
	
	/* 헤더에서 토큰 추출 */
	public String resolveToken(HttpServletRequest request, String type) {
		Enumeration<String> headers = request.getHeaders(HEADER_TOKEN_NAME);
		
		while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value.toLowerCase().startsWith(type.toLowerCase())) {
                return value.substring(type.length()).trim();
            }
        }
		
		return Strings.EMPTY;
	}
	
	/* 토큰 유효성 확인, 만료일자 확인 */
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			
			if(claims.getBody().getExpiration().before(new Date()))
				return false;
			
			String userEmail = (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userEmail");
			if(!tokenService.checkToken(new TokenInfoVo(userEmail, token))) {
				return false;
			}
			
			return true;
		} catch(Exception e) {
			log.error("[validateToken] class = {}, message = {}", e.getClass().getSimpleName(), e.getMessage());
			return false;
		}
	}
	
}
