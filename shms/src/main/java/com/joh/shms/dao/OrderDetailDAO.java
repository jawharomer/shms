package com.joh.shms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.OrderDetail;

public interface OrderDetailDAO extends CrudRepository<OrderDetail, Integer> {

	List<OrderDetail> findAllByOrderOrderTimeBetween(Date from, Date to);
}
