package com.joh.shms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.Customer;
import com.joh.shms.model.Product;

public interface ProductDAO extends CrudRepository<Product, Integer> {
Product findByCode(String code);
}
