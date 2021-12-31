package com.cch.demojwt.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.cch.demojwt.definition.ResponseCode;
import com.cch.demojwt.entity.User;
import com.cch.demojwt.exception.CustomException;
import com.cch.demojwt.repository.UserRepository;
import com.cch.demojwt.response.VO.UserResponseVO;
import com.cch.demojwt.service.UserService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public void delete(String account) {
        // TODO Auto-generated method stub
        userRepository.deleteByAccount(account);
    }

    @Override
    public UserResponseVO search(String account) {
        // TODO Auto-generated method stub
        User u = userRepository.findByAccount(account).orElseThrow(() ->  new CustomException(ResponseCode.ACCOUNT_IS_EXIST));
        return UserResponseVO.builder()
            .username(u.getUsername())
            .account(u.getAccount())
            .email(u.getEmail())
            .roles(u.getRoles())
            .build();
    }
    
}
