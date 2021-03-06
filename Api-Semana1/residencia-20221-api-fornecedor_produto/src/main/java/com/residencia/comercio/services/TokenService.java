package com.residencia.comercio.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.residencia.comercio.entities.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("$/{jwt.secret}")
	private String jwtSecret;
	
	public String generateToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		String tokenJwt = Jwts.builder().setIssuer("IRS").setSubject(usuario.getIdUsuario().toString())
					      .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
		
		return tokenJwt;
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public Integer extractIdFromToken(String token) {
		Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		Integer id = Integer.valueOf(body.getSubject());
		return id;
	}
}
