package com.infosys.usermanagement.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.usermanagement.model.AuthenticationRequest;
import com.infosys.usermanagement.model.AuthenticationResponse;
import com.infosys.usermanagement.service.CustomUserDetailsService;
import com.infosys.usermanagement.util.JWTUtil;

@RestController
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class.getName());
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JWTUtil jwtTokenUtil;
	
	@RequestMapping({ "/hello" })
	public String hello() {
		return "Welcome Kishore Ilaka";
	}

	@RequestMapping(value = "/serviceAuth", method  = RequestMethod.POST)
	 public ResponseEntity<?> createAuthenticationToken(AuthenticationRequest user,Map model,
			 	@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		
		user = (AuthenticationRequest) model.get("user");
		String userName = user.getUserName();

		//logger.info("UserName: " + user.getUserName());
		//logger.info("Password:" + user.getPassword());
	     
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		}catch(BadCredentialsException exception) {
			throw new Exception("Incorrect UserName or Password" + exception.getMessage());
		}
		// Call service layer class and do validation
		final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		if(userDetails !=null && userDetails.getUsername()!=null && userDetails.getPassword()!=null){
			if(userDetails.getPassword().equals(user.getPassword()) && userDetails.getUsername().equals(user.getUserName())){
				// invoke User Details through service and DAO layer... 
			}
		} else if(userDetails == null || userDetails.getUsername() ==null || userDetails.getPassword()==null){
	    	  logger.info("***** userDetails == null || userDetails.getUsername() ==null ****** ");
	  		return (ResponseEntity<?>) ResponseEntity.notFound();
		}
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}	
}
