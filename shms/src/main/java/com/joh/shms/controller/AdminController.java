package com.joh.shms.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.shms.domain.model.NotificationD;
import com.joh.shms.model.Customer;
import com.joh.shms.service.CustomerService;
import com.joh.shms.service.ReportService;
import com.joh.shms.validator.CustomerValidation;

@Controller()
@RequestMapping(path = "/admin")
public class AdminController {

	private static final Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private ReportService reportService;

	@GetMapping()
	public String getAllNotification(Model model) {
		logger.info("getAllNotification->fired");

		List<NotificationD> notificationDs = reportService.findAdminNotifications();
		model.addAttribute("notificationDs", notificationDs);

		return "notifications";
	}

}
