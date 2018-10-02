package com.joh.shms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.OrderDetail;

public interface OrderDetailDAO extends CrudRepository<OrderDetail, Integer> {

}
