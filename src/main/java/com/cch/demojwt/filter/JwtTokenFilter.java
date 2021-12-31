package com.cch.demojwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cch.demojwt.exception.CustomException;
import com.cch.demojwt.security.JwtTokenProvider;
import com.cch.demojwt.service.UserService;
import com.cch.demojwt.service.impl.CustomUserDetailsServiceImpl;
import com.cch.demojwt.service.impl.UserServiceImpl;

import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class JwtTokenFilter  extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;
	// private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
		try {
			if (token != null && jwtTokenProvider.validateToken(token)) {
				Authentication auth = jwtTokenProvider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (CustomException ex) {
			HttpServletResponse response = (HttpServletResponse) res;
			response.sendError(ex.getCode().getCode(), ex.getMessage());
			log.error("Cannot set user authentication: {}", ex);
		}
		filterChain.doFilter(req, res);
    }
    
}
