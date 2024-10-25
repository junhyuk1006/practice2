package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	@GetMapping("/login")
    public String login() {
        return "user/login";  // 로그인 페이지 반환
    }
	@GetMapping("/a/join")
	public String joinForm() {
		return "user/join";
	}
	@PostMapping("/a/join")
	public String join(User user) {
		userService.join(user);
		return "redirect:/";
	}
}
