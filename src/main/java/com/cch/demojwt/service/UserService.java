package com.cch.demojwt.service;

import com.cch.demojwt.response.VO.UserResponseVO;


public interface UserService {

    void delete(String account);

    UserResponseVO search(String account);
}
