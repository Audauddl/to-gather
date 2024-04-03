package com.greedy.togather.user.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greedy.togather.user.user.model.dao.UserMapper;
import com.greedy.togather.user.user.model.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class AuthenticationService implements UserDetailsService {
	
	private final UserMapper mapper;
	
	public AuthenticationService(UserMapper mapper) {
		this.mapper = mapper;
	}
	
	 @Override
	    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

	   
		 
		 	log.debug("userId : {}", userId);
		 	
	        log.info("[AuthenticationService] userId : {}", userId);

	        UserDTO user = mapper.findByUserId(userId);

	        log.info("[AuthenticationService] user : {}", user);

	        if(user == null){
	        	
	            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다");
	        }

	        return user;
	    }

   }


