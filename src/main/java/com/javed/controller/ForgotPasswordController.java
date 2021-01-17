package com.javed.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javed.entity.Customer;
import com.javed.entity.State;
import com.javed.exception.UserNotFoundException;
import com.javed.service.impl.CustomerServiceImpl;
import com.javed.utility.Utility;

import net.bytebuddy.utility.RandomString;
@Controller
@RequestMapping("/login")
public class ForgotPasswordController {

	@Autowired
	private CustomerServiceImpl service;
	
	@Autowired
	private JavaMailSender mail;
	
	@GetMapping("/forgot_password")
	public String showForgotPasswordForm() {
	    return "forgot";
	}
		
	@PostMapping("/forgot_password_save")
	public String processForgotPassword(HttpServletRequest request, Model model) throws UnsupportedEncodingException, MessagingException {
	    String email = request.getParameter("username");
	    String token = RandomString.make(30);
	    
	     
	   System.out.println("email is here" + email);
	   System.out.println("token is here" + token);
	   try {
		   service.updateResetPassword(token, email);
		   String resetPasswordLink = Utility.getSiteURL(request) + "/login/reset_password?token=" + token;
		   
		   sendEmail(email,resetPasswordLink);
		   model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
		   
		   System.out.println(resetPasswordLink);
		   
	} catch (UserNotFoundException e) {
		 model.addAttribute("error", e.getMessage());
	}
	   catch (UnsupportedEncodingException | MessagingException e) {
	        model.addAttribute("error", "Error while sending email");
	    }
	         
	    return "forgot";
	}


	public void sendEmail(String email, String resetPasswordLink)
	        throws MessagingException, UnsupportedEncodingException {
	    MimeMessage message = mail.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom("mohammad.javed1392@gmail.com", "Shopme Support");
	    helper.setTo(email);
	     
	    String subject = "Here's the link to reset your password";
	     
	    String content = "<p>Hello,</p>"
	            + "<p>You have requested to reset your password.</p>"
	            + "<p>Click the link below to change your password:</p>"
	            + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
	            + "<br>"
	            + "<p>Ignore this email if you do remember your password, "
	            + "or you have not made the request.</p>";
	     
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    mail.send(message);
	}
	
	
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
	    Customer customer = service.getByResetPasswordToken(token);
	    model.addAttribute("token", token);
	    if (customer == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "message";
	    }
	    State country=new State();
		model.addAttribute("country", country);
	    return "reset-forgot";
	}
	
	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
	    String token = request.getParameter("token");
	    String password = request.getParameter("password");
	     
	    Customer customer = service.getByResetPasswordToken(token);
	    model.addAttribute("title", "Reset your password");
	     
	    if (customer == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "message";
	    } else {           
	        service.updatePassword(customer, password);
	         
	        model.addAttribute("message", "You have successfully changed your password.");
	    }
	    
	    State country=new State();
		model.addAttribute("country", country);
	    return "Index";
	}
	
	
	
}
