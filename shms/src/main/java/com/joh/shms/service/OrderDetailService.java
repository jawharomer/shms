package com.joh.shms.service;

import com.joh.shms.model.OrderDetail;

public interface OrderDetailService {

	OrderDetail save(OrderDetail orderDetail);

	void delete(OrderDetail orderDetail);

}
