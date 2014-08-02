package com.rlrg.webserver.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	
	@RequestMapping("/login.html")
	public String loginForm(){
		return "login";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "redirect:/dashboard.html";
	}
	
//	@RequestMapping("/dashboard.html")
//	public String dashboard(){
//		return "dashboard";
//	}
	
	@RequestMapping("/upload")
	public String upload(){
		return null;
	}

}