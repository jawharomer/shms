package com.joh.shms.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping(path="/orderDetails")
public class OrderDetailController {

	private static final Logger logger = Logger.getLogger(OrderDetailController.class);

	@GetMapping()
	public String login() {
		
		return "orderDetails";
	}

}
