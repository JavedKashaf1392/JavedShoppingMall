package com.javed.config;
/*package in.nit.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import in.nit.model.User1;
import in.nit.service.UserServiceImpl1;

@Component
public class CustomLoginFailuerHandler extends SimpleUrlAuthenticationFailureHandler{
	
	@Autowired
	private UserServiceImpl1 userService;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("this is starting of config method method");
		
		String email=request.getParameter("username");
		User1 user=userService.getByUserEmails(email);
		if(user!=null) {
			if(user.isAccountNonLocked()) {
				if(user.getFailedAttempt() < userService.MAX_FAILED_ATTEMPT -1) {
					userService.increaseFailedAttempt(user);
				}
				System.out.println("this is config method method");
			}
			
		}else {
			System.out.println("Email not Exist");
		}
		
		
		super.setDefaultFailureUrl("/login?error");
		super.onAuthenticationFailure(request, response, exception);
	}

}
*/