package com.example.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {

	@GetMapping()
	public String index() {
		return "redirect:/login";
	}

	@GetMapping("/user")
	public String adminView(Authentication authentication, Model model) {
		model.addAttribute("my", authentication.getPrincipal());
		return "myinfo";
	}

	@GetMapping("/admin")
	public String userView() {
		return "admin";
	}

}