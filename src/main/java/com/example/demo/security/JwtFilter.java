package com.example.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader=request.getHeader("Authorization");
		if(authHeader!=null&&authHeader!=""&&authHeader.startsWith("Bearer")) {
			String token=authHeader.substring(7);
			try {
				UserDetails details=this.customUserDetailService.loadUserByUsername(jwtUtil.extractUsername(token));
				if(details.getUsername()!=null&& SecurityContextHolder.getContext().getAuthentication()==null)	
				{
					UsernamePasswordAuthenticationToken upat= new UsernamePasswordAuthenticationToken(details,null,details.getAuthorities());
					upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(upat);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
