package com.cch.demojwt.request.VO;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignupRequestVO {
    @NotBlank
    @Size(max = 30)
    private String account;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 12, max = 120)
    private String password;

    private Set<String> role;
}
