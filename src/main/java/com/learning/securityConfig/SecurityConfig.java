package com.learning.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//	@Bean
//	public UserDetailsService getUserDetailsService() {
//		return new UserDetailsServiceImpl();
//	}
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	// We need the bean of BCryptPasswordEncoder for encoding our password
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// We need bean of DaoAuthenticationProvider because we will be our providing
	// security for dao(memory/db) objects
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.userDetailsServiceImpl);
		daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {	 			
		return http.csrf(obj->obj.disable())
                .authorizeHttpRequests(auth -> {
                            auth.requestMatchers("/home/admin/**").hasRole("ADMIN");
                            auth.requestMatchers("/home/user/*").hasRole("NORMAL_USER");
                            auth.requestMatchers("/**").permitAll();
                            
                            auth.anyRequest().authenticated();
                        }
                )
                .formLogin(obj->obj
                		.loginPage("/login")
                		.loginProcessingUrl("/dologin")
                		.defaultSuccessUrl("/home/admin/index")
                		)
                .build();
	}
}
