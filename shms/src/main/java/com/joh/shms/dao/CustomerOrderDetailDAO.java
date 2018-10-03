package com.joh.shms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.CustomerOrderDetail;
import com.joh.shms.model.OrderDetail;

public interface CustomerOrderDetailDAO extends CrudRepository<CustomerOrderDetail, Integer> {

	List<CustomerOrderDetail> findAllByCustomerOrderOrderTimeBetween(Date from, Date to);
}
