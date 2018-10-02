package com.joh.shms.service;

import com.joh.shms.model.Vendor;

public interface VendorService {

	Iterable<Vendor> findAll();

	void delete(int id);

	Vendor findOne(int id);

	Vendor save(Vendor vendor);

}
