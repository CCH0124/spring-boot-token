package com.cch.demojwt.request.VO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestVO {
    
    @NotBlank
    @Size(max = 20)
    private String account;

    
    @NotBlank
	@Size(max = 120)
    private String password;
}
