package com.joh.shms.service;

import com.joh.shms.model.Customer;

public interface CustomerService {

	Iterable<Customer> findAll();

	Customer save(Customer customer);

	Customer findOne(int id);

	void delete(int id);

}
