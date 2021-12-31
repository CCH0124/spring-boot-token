package com.cch.demojwt.response.VO;

import java.util.HashSet;
import java.util.Set;

import com.cch.demojwt.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseVO {

    private String id;
    
	private String account;

	private String username;

	private String email;

    private Set<Role> roles = new HashSet<>();
}
