package com.metanet.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ServletRequestBindingException.class)
	public String sessionException(Exception e, Model model) {
		model.addAttribute("exception", e);
		return "/errors/loginErrorPage";
	}
	
	@ExceptionHandler(Exception.class) 
	public String handleException(Exception e, Model model) {
		model.addAttribute("exception", e);
		return "/errors/errorPage";
	}
}
