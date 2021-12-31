package com.cch.demojwt.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.cch.demojwt.entity.User;
import com.cch.demojwt.repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.findByAccount(username).orElseThrow(() -> new UsernameNotFoundException("Account '" + username + "' not found"));
		return UserDetailsImpl.build(user);
    }


}
