package com.joh.shms.service;

import java.util.Date;
import java.util.List;

import com.joh.shms.model.CustomerOrderDetail;

public interface CustomerOrderDetailService {

	CustomerOrderDetail save(CustomerOrderDetail customerOrderDetail);

	List<CustomerOrderDetail> findAllByOrderTime(Date from, Date to);

	

	void delete(CustomerOrderDetail customerOrderDetail);

}
