package com.joh.shms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.ProductCategory;

public interface ProductCategoryDAO extends CrudRepository<ProductCategory, Integer> {

}
