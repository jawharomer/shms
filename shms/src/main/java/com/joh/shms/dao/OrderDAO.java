package com.joh.shms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.Order;

public interface OrderDAO extends CrudRepository<Order, Integer> {
	List<Order> findAllByOrderTimeBetween(Date form, Date to);
}
