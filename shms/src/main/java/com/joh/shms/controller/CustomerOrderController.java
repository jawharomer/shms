package com.joh.shms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.shms.exception.CusDataIntegrityViolationException;
import com.joh.shms.model.Customer;
import com.joh.shms.model.CustomerOrder;
import com.joh.shms.service.CustomerOrderService;
import com.joh.shms.service.CustomerService;
import com.joh.shms.service.ProductCategoryService;

@Controller()
@RequestMapping(path = "/customerOrders")
public class CustomerOrderController {

	private static final Logger logger = Logger.getLogger(CustomerOrderController.class);

	@Autowired
	private CustomerOrderService customerOrderService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductCategoryService productCategoryService;

	// @GetMapping()
	// public String getAllCustomerOrder(@RequestParam() @DateTimeFormat(pattern =
	// "yyyy-MM-dd") Date from,
	// @RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model)
	// {
	// logger.info("getAllOrder->fired");
	// logger.info("from=" + from);
	// logger.info("to=" + to);
	//
	// List<Order> orders = orderService.findAllByOrderTimeBetween(from, to);
	//
	// logger.info("orders=" + orders);
	//
	// model.addAttribute("orders", orders);
	// model.addAttribute("from", from);
	// model.addAttribute("to", to);
	//
	// return "getAllOrder";
	//
	// }

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
	public String addCustomerOrder(@RequestBody @Valid CustomerOrder customerOrder, BindingResult result, Model model)
			throws JsonProcessingException {
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
	//
	// @GetMapping(path = "/edit/{id}")
	// public String getEditingOrder(@PathVariable int id, Model model) throws
	// JsonProcessingException {
	// logger.info("getEditingOrder->fired");
	// logger.info("orderId=" + id);
	//
	// ObjectMapper mapper = new ObjectMapper();
	//
	// Iterable<Vendor> vendors = vendorService.findAll();
	//
	// logger.info("vendors=" + vendors);
	//
	// model.addAttribute("jsonVendors", mapper.writeValueAsString(vendors));
	//
	// Iterable<ProductCategory> productCategories =
	// productCategoryService.findAll();
	//
	// logger.info("productCategories=" + productCategories);
	//
	// model.addAttribute("jsonProductCategories",
	// mapper.writeValueAsString(productCategories));
	//
	// Order order = orderService.findOne(id);
	// logger.info("order=" + order);
	//
	// model.addAttribute("jsonOrder", mapper.writeValueAsString(order));
	//
	// return "editOrder";
	// }
	//
	// @PostMapping(path = "/update")
	// public String updateOrder(@RequestBody @Valid Order order, BindingResult
	// result, Model model)
	// throws JsonProcessingException {
	// logger.info("updateOrder->fired");
	//
	// logger.info("order=" + order);
	//
	// logger.info("errors=" + result.getAllErrors());
	//
	// if (result.hasErrors()) {
	// throw new CusDataIntegrityViolationException("Please fill data properly");
	// } else {
	// orderService.update(order);
	// return "success";
	// }
	//
	// }
	//
	// @PostMapping(path = "/delete/{id}")
	// public String deleteOrder(@PathVariable int id) throws
	// JsonProcessingException {
	// logger.info("deleteOrder->fired");
	// logger.info("orderId=" + id);
	// orderService.delete(id);
	// return "success";
	//
	// }

}
