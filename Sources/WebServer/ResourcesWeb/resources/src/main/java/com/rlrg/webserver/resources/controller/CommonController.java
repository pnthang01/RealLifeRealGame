package com.rlrg.webserver.resources.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

	@RequestMapping(value="/welcome")
	public String index(){
		return "index";	
	}


}
