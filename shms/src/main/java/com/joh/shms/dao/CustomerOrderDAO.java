package com.joh.shms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.CustomerOrder;
import com.joh.shms.model.Order;

public interface CustomerOrderDAO extends CrudRepository<CustomerOrder, Integer> {
	List<CustomerOrder> findAllByOrderTimeBetween(Date from, Date to);
}
