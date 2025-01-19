package com.example.demo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/* UserDetails
 * 내가 정의한 User의 값들 get메소드만 있음
 * ( Provides core user information ) 
 */
public class CustomUserDetails implements UserDetails {

	private final User user;

	private final Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}

    public int getAge() { // 이런식으로 User정보를 이용가능
        return user.getAge();
    }
    
	public User getUser() {
		return user;
	}
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	/*
	 * isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled()
	 * : 계정의 상태를 나타냄
	 * 
	 * 기본적으로 새 계정은 다 true인 상태라, 임시로 간단히 해논거
	 */
	
	// 계정이 만료되었는지
	@Override
	public boolean isAccountNonExpired() {
		// return user.isAccountNonExpired();
		return true;
	}

	// 계정이 잠겨 있는지
	@Override
	public boolean isAccountNonLocked() {
		// return user.isAccountNonLocked();
		return true;
	}

	// 계정의 자격 증명이 만료되었는지
	@Override
	public boolean isCredentialsNonExpired() {
		// return user.isCredentialsNonExpired();
		return true;
	}

	// 계정이 활성화되었는지
	@Override
	public boolean isEnabled() {
		// return user.isEnabled();
		return true;
	}	


}
