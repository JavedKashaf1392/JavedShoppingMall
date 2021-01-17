package com.javed.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) 
			throws Exception
	{
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
		
		
		
		
		.antMatchers("/customer/**","/css/**", "/js/**", "/image/**","/image/product/","/fonts/**","/","/index/**","/getStates","/customer/customerSave","/login/**","/login/reset_password").permitAll()
		.antMatchers("/product/update").hasAuthority("ADMIN")
        .antMatchers("/product/update").hasAuthority("CUSTOMER")
		.anyRequest().permitAll()
		
		//form login 
		.and()
		.formLogin()
		.loginProcessingUrl("/login").permitAll()
				.loginPage("/customer/login") //GET- Show Login page
				 //POST-process login
//				.defaultSuccessUrl("/customer/setup",false)
		        .defaultSuccessUrl("/",false)
				.failureUrl("/login?error") //on login fail
				
				.failureUrl("/customer/login?error")
				
				.and()
				.logout()
			    .logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
				.logoutSuccessUrl("/customer/login?logout")
				
				.and()
				.exceptionHandling()
				.accessDeniedPage("/customer/denied");		
}
}
