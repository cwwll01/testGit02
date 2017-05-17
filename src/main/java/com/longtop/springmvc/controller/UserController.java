package com.longtop.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.longtop.springmvc.Bs.IPlmBasService;

@Controller
public class UserController {
	@Autowired
	private IPlmBasService plmbasService;
	
	
	@RequestMapping("login")
	public String getPage(){
		
		plmbasService.run();
		
		
		return "WEB-INF/jsp/login.jsp";
	}
}
