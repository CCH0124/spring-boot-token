package com.cch.demojwt.response.VO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseVO {
    private String token;
    @Builder.Default
    private String type = "Bearer";
    private String id;
    private String username;
    private String account;
    private String email;
    private List<String> roles;
}
