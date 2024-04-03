package com.greedy.togather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.greedy.togather.user.user.service.AuthenticationService;


@EnableWebSecurity
public class SecurityConfig {
	
	private final AuthenticationService authenticationService;
	
	public SecurityConfig(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return http
				.csrf()
					.disable()
					.authorizeHttpRequests()

					.antMatchers("/user/mypage" ,"/user/project", "/user/project/detail", "/user/project/create", "/user/pay").hasRole("USER")

					.antMatchers("/admin/*").hasRole("ADMIN")
					.anyRequest().permitAll()
				.and()
					.formLogin()
					.loginPage("/user/login")
				    .defaultSuccessUrl("/")  
					.failureForwardUrl("/user/loginfail")
					.usernameParameter("userId")			
                    .passwordParameter("userPwd")			
				.and()
					.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.invalidateHttpSession(true)
					.logoutSuccessUrl("/")
				.and()
					.build();
					
	}
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		
		return http
				.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(authenticationService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
	

}
















