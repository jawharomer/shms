package com.joh.shms.service;

import java.util.Date;
import java.util.List;

import com.joh.shms.model.OrderDetail;

public interface OrderDetailService {

	OrderDetail save(OrderDetail orderDetail);

	void delete(OrderDetail orderDetail);

	List<OrderDetail> findAllByOrderTime(Date from, Date to);

	OrderDetail findFirstByProductCode(String code);

}
