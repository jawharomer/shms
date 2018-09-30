package com.joh.shms.controller;

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

import com.joh.shms.model.Customer;
import com.joh.shms.service.CustomerService;
import com.joh.shms.validator.CustomerValidation;

@Controller()
@RequestMapping(path = "/customers")
public class CustomerController {

	private static final Logger logger = Logger.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping()
	public String getAllCustomer(Model model) {
		logger.info("getAddingCustomer->fired");
		model.addAttribute("customers", customerService.findAll());

		return "customers";
	}

	@GetMapping(path = "/add")
	public String getAddingCustomer(Model model) {
		logger.info("getAddingCustomer->fired");

		model.addAttribute("customer", new Customer());

		return "customer/addCustomer";
	}

	@PostMapping(path = "/add")
	public String addCustomer(@RequestBody @Valid Customer customer, BindingResult result, Model model) {
		logger.info("addCustomer->fired");

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {

			model.addAttribute("customer", customer);
			return "customer/addCustomer";
		} else {
			customerService.save(customer);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingCustomer(@PathVariable int id, Model model) {
		logger.info("getEditingCustomer->fired");
		logger.info("customerId=" + id);

		Customer customer = customerService.findOne(id);
		logger.info("customer=" + customer);

		model.addAttribute("customer", customer);

		return "customer/editCustomer";
	}

	@PostMapping(path = "/edit")
	public String editCustomer(@RequestBody @Validated(CustomerValidation.Insert.class) Customer customer,
			BindingResult result, Model model) {
		logger.info("editCustomer->fired");

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {

			model.addAttribute("customer", customer);
			return "customer/editCustomer";
		} else {
			customerService.save(customer);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteCustomer(@PathVariable int id) {
		logger.info("deleteCustomer->fired");
		logger.info("customerId=" + id);
		customerService.delete(id);
		return "success";
	}
}
