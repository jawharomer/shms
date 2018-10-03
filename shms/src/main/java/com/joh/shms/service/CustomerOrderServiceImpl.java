package com.joh.shms.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.shms.dao.CustomerOrderDAO;
import com.joh.shms.exception.CusDataIntegrityViolationException;
import com.joh.shms.model.CustomerOrder;
import com.joh.shms.model.CustomerOrderDetail;
import com.joh.shms.model.OrderDetail;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderDAO customerOrderDAO;

	@Autowired
	private CustomerOrderDetailService customerOrderDetailService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Override
	@Transactional
	public CustomerOrder save(CustomerOrder customerOrder) {
		CustomerOrder savedCustomerOrder = customerOrderDAO.save(customerOrder);
		customerOrder.getCustomerOrderDetails().stream().forEach(e -> {
			e.setCustomerOrder(customerOrder);
			OrderDetail orderDetail = orderDetailService.findFirstByProductCode(e.getProduct().getCode());
			if (orderDetail == null) {
				throw new CusDataIntegrityViolationException(
						"This product is out of the stock now the code=" + e.getProduct().getCode());
			}

			e.setOrderDetail(orderDetail);
			customerOrderDetailService.save(e);
		});
		return savedCustomerOrder;

	}

	@Override
	public List<CustomerOrder> findAllByOrderTimeBetween(Date from, Date to) {
		return customerOrderDAO.findAllByOrderTimeBetween(from, to);
	}

	@Override
	public CustomerOrder findOne(int id) {
		return customerOrderDAO.findOne(id);
	}

	@Transactional
	@Override
	public CustomerOrder update(CustomerOrder customerOrder) {

		List<CustomerOrderDetail> oldCustomerOrderDetails = customerOrderDAO.findOne(customerOrder.getId())
				.getCustomerOrderDetails();

		for (CustomerOrderDetail customerOrderDetail : oldCustomerOrderDetails) {

			boolean isExists = customerOrder.getCustomerOrderDetails().stream()
					.anyMatch(e -> e.getId() == customerOrderDetail.getId());

			if (!isExists) {
				customerOrderDetailService.delete(customerOrderDetail);
			}

		}

		customerOrder.getCustomerOrderDetails().stream().filter(e -> e.getId() == null).forEach(e -> {
			e.setCustomerOrder(customerOrder);
			OrderDetail orderDetail = orderDetailService.findFirstByProductCode(e.getProduct().getCode());
			if (orderDetail == null) {
				throw new CusDataIntegrityViolationException(
						"This product is out of the stock now the code=" + e.getProduct().getCode());
			}
			e.setOrderDetail(orderDetail);
			customerOrderDetailService.save(e);
		});

		return customerOrderDAO.save(customerOrder);
	}

	@Transactional
	@Override
	public void delete(int id) {
		CustomerOrder customerOrder = customerOrderDAO.findOne(id);
		customerOrder.getCustomerOrderDetails().stream().forEach(e -> {
			customerOrderDetailService.delete(e);
		});
		customerOrderDAO.delete(id);
	}
}
