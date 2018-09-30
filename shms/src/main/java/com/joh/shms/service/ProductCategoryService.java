package com.joh.shms.service;

import com.joh.shms.model.ProductCategory;

public interface ProductCategoryService {

	ProductCategory save(ProductCategory productCategory);

	void delete(int id);

	ProductCategory findOne(int id);

	Iterable<ProductCategory> findAll();

}
