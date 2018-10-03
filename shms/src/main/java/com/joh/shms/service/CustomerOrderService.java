package com.joh.shms.service;

import java.util.Date;
import java.util.List;

import com.joh.shms.model.CustomerOrder;

public interface CustomerOrderService {

	CustomerOrder save(CustomerOrder customerOrder);

	List<CustomerOrder> findAllByOrderTimeBetween(Date from, Date to);

	CustomerOrder update(CustomerOrder customerOrder);

	CustomerOrder findOne(int id);

	void delete(int id);

}
