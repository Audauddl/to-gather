package com.greedy.togather.user.user.model.dto;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDTO implements UserDetails {
	
	private String userNo;									
	private String email;						
	private String pwd;								
	private String userNm;							
	private String profileNm;
	private String phone;
	private String address;
	private Date joinDate;							
	private Date quitDate;							
	private String quitYn;		
	private String userToken;
	private String confrimCode;
	private List<UserRoleDTO> userRole;
	
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(UserRoleDTO role : userRole) {
        	roles.add(new SimpleGrantedAuthority(role.getAuth().getAuthNm()));
        }
        
        return roles;
    }


	@Override
	public String getPassword() {
		return pwd;
	}


	@Override
	public String getUsername() {
		return email;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}

}
