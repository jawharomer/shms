package com.joh.shms.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.shms.exception.CusDataIntegrityViolationException;
import com.joh.shms.model.Customer;
import com.joh.shms.model.CustomerOrder;
import com.joh.shms.model.Order;
import com.joh.shms.service.CustomerOrderService;
import com.joh.shms.service.CustomerService;
import com.joh.shms.validator.CustomerOrderValidation;

@Controller()
@RequestMapping(path = "/customerOrders")
public class CustomerOrderController {

	private static final Logger logger = Logger.getLogger(CustomerOrderController.class);

	@Autowired
	private CustomerOrderService customerOrderService;

	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/{id}")
	public String getCustomerOrder(@PathVariable int id, Model model) {
		logger.info("getCustomerOrder->fired");
		logger.info("customerOrderId=" + id);

		CustomerOrder customerOrder = customerOrderService.findOne(id);

		logger.info("customerOrder=" + customerOrder);

		model.addAttribute("customerOrder", customerOrder);

		return "getCustomerOrder";
	}

	@GetMapping()
	public String getAllCustomerOrder(@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllOrder->fired");
		logger.info("from=" + from);
		logger.info("to=" + to);

		List<CustomerOrder> customerOrders = customerOrderService.findAllByOrderTimeBetween(from, to);

		logger.info("customerOrders=" + customerOrders);

		model.addAttribute("customerOrders", customerOrders);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "getAllCustomerOrder";

	}

	@GetMapping(path = "/add")
	public String getAddingCustomerOrder(Model model) throws JsonProcessingException {
		logger.info("getAddingCustomerOrder->fired");
		ObjectMapper mapper = new ObjectMapper();

		Iterable<Customer> customers = customerService.findAll();

		logger.info("customers=" + customers);

		model.addAttribute("jsonCustomers", mapper.writeValueAsString(customers));

		CustomerOrder customerOrder = new CustomerOrder();
		logger.info("customerOrder=" + customerOrder);

		model.addAttribute("jsonCustomerOrder", mapper.writeValueAsString(customerOrder));

		return "addCustomerOrder";
	}

	@PostMapping(path = "/add")
	public String addCustomerOrder(
			@RequestBody @Validated(CustomerOrderValidation.Insert.class) CustomerOrder customerOrder,
			BindingResult result, Model model) throws JsonProcessingException {
		logger.info("addCustomerOrder->fired");

		logger.info("customerOrder=" + customerOrder);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			throw new CusDataIntegrityViolationException("Please fill data properly");
		} else {
			customerOrderService.save(customerOrder);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingCustomerOrder(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getEditingCustomerOrder->fired");
		ObjectMapper mapper = new ObjectMapper();

		Iterable<Customer> customers = customerService.findAll();

		logger.info("customers=" + customers);

		model.addAttribute("jsonCustomers", mapper.writeValueAsString(customers));

		CustomerOrder customerOrder = customerOrderService.findOne(id);
		logger.info("customerOrder=" + customerOrder);

		model.addAttribute("jsonCustomerOrder", mapper.writeValueAsString(customerOrder));

		return "editCustomerOrder";
	}

	@PostMapping(path = "/update")
	public String updateCustomerOrder(@RequestBody @Valid CustomerOrder customerOrder, BindingResult result,
			Model model) throws JsonProcessingException {
		logger.info("updateCustomerOrder->fired");

		logger.info("customerOrder=" + customerOrder);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			throw new CusDataIntegrityViolationException("Please fill data properly");
		} else {
			customerOrderService.update(customerOrder);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteOrder(@PathVariable int id) throws JsonProcessingException {
		logger.info("deleteOrder->fired");
		logger.info("customerOrderId=" + id);
		customerOrderService.delete(id);
		return "success";

	}

}
