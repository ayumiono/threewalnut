package mhao.threewalnut.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.DefaultJws;
import io.jsonwebtoken.impl.TextCodec;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mhao.threewalnut.bean.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);
	private static final JwtParser jwt_parser = Jwts.parser().setSigningKey(TextCodec.BASE64.encode(Config.getPropertyAsString("secret_key")));
	
	public static String generateToken(String uid,String businessPhone,String phone,String mid,Date exp){
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Map<String, String> payload = new HashMap<String, String>();
		payload.put("uid", uid);
		payload.put("businessPhone", businessPhone);
		payload.put("phone", phone);
		payload.put("mid", mid);
		payload.put("exp", exp.getTime()+"");
		return Jwts.builder().signWith(signatureAlgorithm, TextCodec.BASE64.encode(Config.getPropertyAsString("secret_key"))).setPayload(GsonUtil.toGson(payload)).compact();
	}
	
	public static class JWTWraper{
		DefaultJws<Object> jws;
		JWTWraper(DefaultJws<Object> jws){
			this.jws = jws;
		}
		public String getUid(){
			return ((Claims)this.jws.getBody()).get("uid", String.class);
		}
		public String getMid(){
			return ((Claims)this.jws.getBody()).get("mid", String.class);
		}
		public Date getExp(){
			return ((Claims)this.jws.getBody()).getExpiration();
		}
		public String getPhone(){
			return ((Claims)this.jws.getBody()).get("phone", String.class);
		}
		public String getBusinessphone(){
			return ((Claims)this.jws.getBody()).get("businessPhone", String.class);
		}
		public String getSignature(){
			return this.jws.getSignature();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static JWTWraper validToken(String jwt){
		try {
			return new JWTWraper((DefaultJws<Object>)jwt_parser.parse(jwt));
		} catch (ExpiredJwtException e) {
			logger.info("token expired:{}",jwt);
		} catch (SignatureException e) {
			logger.info("wrong token:{}",jwt);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
}
