package com.cch.demojwt.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.cch.demojwt.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
    private String id;

	private String username;

    private String account;

	private String email;

    private Collection<? extends GrantedAuthority> authorities;

	@JsonIgnore
	private String password;

    public UserDetailsImpl(String id, String username, String email, String password, String account,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
        this.account = account;
		this.authorities = authorities;
	}

    public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
		return new UserDetailsImpl(
				user.getId().toString(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
                user.getAccount(),
				authorities);
	}
    

    public String getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}

    public String getAccount() {
		return this.account;
	}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return this.authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
