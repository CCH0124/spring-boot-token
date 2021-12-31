package com.cch.demojwt.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.cch.demojwt.definition.ERole;
import com.cch.demojwt.definition.ResponseCode;
import com.cch.demojwt.entity.Role;
import com.cch.demojwt.entity.User;
import com.cch.demojwt.exception.CustomException;
import com.cch.demojwt.repository.RoleRepository;
import com.cch.demojwt.repository.UserRepository;
import com.cch.demojwt.request.VO.LoginRequestVO;
import com.cch.demojwt.request.VO.SignupRequestVO;
import com.cch.demojwt.response.VO.JwtResponseVO;
import com.cch.demojwt.security.JwtTokenProvider;
import com.cch.demojwt.service.AuthService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder encoder;
	private final JwtTokenProvider jwtUtils;

    @Override
    public JwtResponseVO signin(LoginRequestVO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getAccount(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        String jwt = jwtUtils.createToken(authentication);
        return JwtResponseVO.builder()
                .token(jwt)
                .username(userDetails.getUsername())
                .account(userDetails.getAccount())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    @Override
    public void signup(SignupRequestVO signupRequestVO) {
        // TODO Auto-generated method stub
        boolean isExist = userRepository.existsByAccount(signupRequestVO.getAccount());
        if (isExist) {
            throw new CustomException(ResponseCode.ACCOUNT_IS_EXIST);
        }
        boolean emailIsExist = userRepository.existsByEmail(signupRequestVO.getEmail());
        if (emailIsExist) {
            throw new CustomException(ResponseCode.EMAIL_IS_EXIST);
        }
        Set<String> strRoles = signupRequestVO.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "moderator":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

        User userObj = new User(null, signupRequestVO.getAccount(), signupRequestVO.getUsername(), signupRequestVO.getEmail(), encoder.encode(signupRequestVO.getPassword()), roles);
        userRepository.save(userObj);
    }

    
    
}
