package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public HttpFirewall allowUrlEncodedDoubleSlash() {
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	    firewall.setAllowUrlEncodedDoubleSlash(true);
	    return firewall;
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/", "/a/**", "/login", "/logout").permitAll()  // 로그인과 로그아웃 경로를 인증 없이 접근 가능하도록 설정
                                .anyRequest().authenticated()  // 그 외 모든 요청은 인증 필요
                )
                .formLogin((form) -> form
                                .loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/")
                )
                .logout((logout) -> logout
                                .logoutSuccessUrl("/")
                                .permitAll()
                )
                .csrf(csrf -> csrf.disable());  // CSRF 비활성화

	    return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
