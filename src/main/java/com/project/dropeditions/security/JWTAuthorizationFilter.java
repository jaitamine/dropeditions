package com.project.dropeditions.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		
		  response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		  response.setHeader("Access-Control-Allow-Headers",
		  "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,Authorization"
		  ); 
		  response.setHeader("Access-Control-Expose-Headers",
		  "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Authorization"
		  );
		  
		  
		 
		String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
		System.out.println("token is : " + jwt);
		if(request.getMethod().equals("OPTIONS")) {		
			response.setStatus(HttpServletResponse.SC_OK);
		}
		
		else {
			
		if (jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;

		}
		System.out.println("in authorize filter");
		Claims claims = Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody();
		System.out.println("clames are: " + claims);
		String username = claims.getSubject();
		
		System.out.println("username is: " + username);
		ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		
		roles.forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.get("authority")));
		});
		UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(username,
				null, authorities);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		filterChain.doFilter(request, response);
		System.out.println("headers ar " + response.getHeaders("Access-Control-Allow-Origin"));
	}
	}
}
