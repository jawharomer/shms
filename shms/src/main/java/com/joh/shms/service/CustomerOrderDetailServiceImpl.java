package com.joh.shms.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.joh.shms.dao.CustomerOrderDetailDAO;
import com.joh.shms.dao.OrderDetailDAO;
import com.joh.shms.exception.CusDataIntegrityViolationException;
import com.joh.shms.model.CustomerOrderDetail;
import com.joh.shms.model.OrderDetail;
import com.joh.shms.model.Product;

@Service
public class CustomerOrderDetailServiceImpl implements CustomerOrderDetailService {

	@Autowired
	private CustomerOrderDetailDAO customerOrderDetailDAO;

	@Autowired
	private OrderDetailDAO orderDetailDAO;

	@Transactional
	@Override
	public CustomerOrderDetail save(CustomerOrderDetail customerOrderDetail) {

		orderDetailDAO.stockDown(customerOrderDetail.getQuantity(), customerOrderDetail.getOrderDetail().getId());

		return customerOrderDetailDAO.save(customerOrderDetail);
	}

	@Transactional
	@Override
	public void delete(CustomerOrderDetail customerOrderDetail) {

		orderDetailDAO.stockUp(customerOrderDetail.getQuantity(), customerOrderDetail.getOrderDetail().getId());
		customerOrderDetailDAO.delete(customerOrderDetail.getId());
	}

	@Override
	public List<CustomerOrderDetail> findAllByOrderTime(Date from, Date to) {
		return customerOrderDetailDAO.findAllByCustomerOrderOrderTimeBetween(from, to);
	}

}
