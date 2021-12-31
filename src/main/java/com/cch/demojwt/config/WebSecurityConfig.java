package com.cch.demojwt.config;

import com.cch.demojwt.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests()
			.antMatchers("/api/v1/user/hello").permitAll()
            .antMatchers("/api/v1/auth/**").permitAll()
			.antMatchers("/api/v1/user/**").permitAll()
            .anyRequest().authenticated();
        // http.exceptionHandling().accessDeniedPage("/login");
		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    }

    // @Override
	// public void configure(WebSecurity web) throws Exception {
	// 	// Allow swagger to be accessed without authentication
	// 	web.ignoring().antMatchers("/v2/api-docs")//
	// 			.antMatchers("/swagger-resources/**")//
	// 			.antMatchers("/swagger-ui.html")//
	// 			.antMatchers("/configuration/**")//
	// 			.antMatchers("/webjars/**")//
	// 			.antMatchers("/public")
	// 			// Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
	// 			.and().ignoring().antMatchers("/h2-console/**/**");
	// 	;
	// }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
