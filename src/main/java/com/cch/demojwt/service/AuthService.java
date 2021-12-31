package com.cch.demojwt.service;

import com.cch.demojwt.request.VO.LoginRequestVO;
import com.cch.demojwt.request.VO.SignupRequestVO;
import com.cch.demojwt.response.VO.JwtResponseVO;

public interface AuthService {
    JwtResponseVO signin(LoginRequestVO loginRequest);
    void signup(SignupRequestVO signupRequestVO);
}
