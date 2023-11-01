package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginIncomingDto;
import com.example.demo.security.CustomUserDetailService;
import com.example.demo.security.JwtUtil;

@RestController
@ResponseBody
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	
	@PostMapping("/")
	public ResponseEntity<?> LoginUser(@RequestBody LoginIncomingDto dto){
		try {
			 	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword());
			    Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
			    SecurityContextHolder.getContext().setAuthentication(authentication);
			    if(authentication.isAuthenticated()) {			    	
			    	UserDetails userDetails=this.customUserDetailService.loadUserByUsername(dto.getUsername());
			    	String token=this.jwtUtil.generateToken(userDetails);
			    	return ResponseEntity.ok(token);
			    }
		}
		catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found !");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong!");
	}
}
