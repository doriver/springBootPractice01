package com.example.demo.domain;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private Long id;
	
	private String username;
	
	private String password;
	
	private int age;
	
	private boolean accountNonExpired;
	
	private boolean accountNonLocked;
	
	private boolean credentialsNonExpired;
	
	private boolean enabled;
	
	private Set<GrantedAuthority> authorities;
	
}
