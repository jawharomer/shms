package com.joh.shms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.shms.dao.CustomerDAO;
import com.joh.shms.dao.ProductDAO;
import com.joh.shms.model.Customer;
import com.joh.shms.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public Product findByCode(String code) {
		return productDAO.findByCode(code);
	}

	@Override
	public Product save(Product product) {
		return productDAO.save(product);
	}

}
