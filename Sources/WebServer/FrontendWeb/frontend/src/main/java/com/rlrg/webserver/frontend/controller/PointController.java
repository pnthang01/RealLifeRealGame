package com.rlrg.webserver.frontend.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.webserver.frontend.domain.PointDisplay;

@Controller
@RequestMapping(value = "/point")
public class PointController {
	private static final Logger LOG = LoggerFactory.getLogger(PointController.class);
	
	@RequestMapping(value = "/getUserPoint", method=RequestMethod.GET)
	public @ResponseBody PointDisplay getUserPoint(){
		try {
			PointDisplay pointDisplay = new PointDisplay();
			pointDisplay.setDisplay("http://localhost:8080/frontend/main/images/pic1.jpg");
			pointDisplay.setPoint(new Random().nextInt());
			pointDisplay.setRanked("Awesome");
			return pointDisplay;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
}
