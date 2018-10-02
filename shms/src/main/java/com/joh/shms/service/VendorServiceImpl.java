package com.joh.shms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.shms.dao.CustomerDAO;
import com.joh.shms.dao.ProductCategoryDAO;
import com.joh.shms.dao.VendorDAO;
import com.joh.shms.model.Customer;
import com.joh.shms.model.ProductCategory;
import com.joh.shms.model.Vendor;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorDAO vendorDAO;

	@Override
	public Iterable<Vendor> findAll() {
		return vendorDAO.findAll();
	}

	@Override
	public Vendor save(Vendor vendor) {
		return vendorDAO.save(vendor);
	}

	@Override
	public Vendor findOne(int id) {
		return vendorDAO.findOne(id);
	}

	@Override
	public void delete(int id) {
		vendorDAO.delete(id);
	}

}
