package com.cch.demojwt.controller;

import javax.validation.Valid;

import com.cch.demojwt.entity.User;
import com.cch.demojwt.request.VO.LoginRequestVO;
import com.cch.demojwt.request.VO.SignupRequestVO;
import com.cch.demojwt.response.VO.ResponseResult;
import com.cch.demojwt.response.VO.UserResponseVO;
import com.cch.demojwt.service.UserService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping(value = "/{account}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable String account) {
		userService.delete(account);
	}

    @GetMapping(value = "/{account}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<ResponseResult<UserResponseVO>> search(@PathVariable String account) {
		return ResponseEntity.ok(ResponseResult.success(userService.search(account)));
	}

    @GetMapping(value = "/hello")
    public String Hello() {
        return "Hello";
    }
}
