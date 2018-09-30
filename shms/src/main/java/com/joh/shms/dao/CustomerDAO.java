package com.joh.shms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.Customer;

public interface CustomerDAO extends CrudRepository<Customer, Integer> {

}
