package com.joh.shms.service;

import com.joh.shms.model.Product;

public interface ProductService {

	Product findByCode(String code);

	Product save(Product product);

}
