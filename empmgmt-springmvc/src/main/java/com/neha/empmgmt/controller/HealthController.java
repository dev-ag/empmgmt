package com.neha.empmgmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value="/health.html")
public class HealthController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getHealth(){
		return "health";
	}
}
