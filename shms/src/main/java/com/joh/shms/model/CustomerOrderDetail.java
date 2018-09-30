package com.joh.shms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ORDER_DETAILS")
public class CustomerOrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_CUSTOMER_ORDER_DETAIL")
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "I_ORDER_DETAIL", nullable = false)
	private OrderDetail orderDetail;

	@ManyToOne()
	@JoinColumn(name = "I_PRODUCT", nullable = false)
	private Product product;

	@Column(name = "QUANTITY")
	private Integer quantity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CustomerOrderDetail [id=" + id + ", orderDetail=" + orderDetail + ", product=" + product + ", quantity="
				+ quantity + "]";
	}

}
