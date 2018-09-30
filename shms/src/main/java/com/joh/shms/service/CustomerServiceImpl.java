package com.joh.shms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.shms.dao.CustomerDAO;
import com.joh.shms.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public Iterable<Customer> findAll() {
		return customerDAO.findAll();
	}

	@Override
	public Customer save(Customer customer) {
		return customerDAO.save(customer);
	}

	@Override
	public Customer findOne(int id) {
		return customerDAO.findOne(id);
	}

	@Override
	public void delete(int id) {
		customerDAO.delete(id);
	}

}
