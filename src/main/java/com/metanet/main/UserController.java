package com.metanet.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping("/main")
	public String main() {
		return "index";
	}
	
	@RequestMapping("/joinform")
	public String joinForm() {
		return "joinForm";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute("user") UserVO vo) {
		service.register(vo);
		return "index";
	}
}
