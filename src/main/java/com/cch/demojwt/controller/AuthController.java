package com.cch.demojwt.controller;

import javax.validation.Valid;

import com.cch.demojwt.request.VO.LoginRequestVO;
import com.cch.demojwt.request.VO.SignupRequestVO;
import com.cch.demojwt.request.VO.TokenRefreshRequest;
import com.cch.demojwt.response.VO.JwtResponseVO;
import com.cch.demojwt.response.VO.ResponseResult;
import com.cch.demojwt.response.VO.TokenRefreshResponseVO;
import com.cch.demojwt.service.AuthService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authServiceImpl;
    
    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponseVO> authenticateUser(@RequestBody @Valid  LoginRequestVO loginRequest) {
        return ResponseEntity.ok(authServiceImpl.signin(loginRequest));
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseResult<Void>> registerUser(@RequestBody @Valid SignupRequestVO signUpRequest) {
        authServiceImpl.signup(signUpRequest);
        return ResponseEntity.ok(ResponseResult.success());
    }

    @PostMapping(value = "/refreshtoken", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseResult<TokenRefreshResponseVO>> refreshToken(@RequestBody @Valid  TokenRefreshRequest request) {
        return ResponseEntity.ok(ResponseResult.success(authServiceImpl.refreshToken(request)));
    }
}
