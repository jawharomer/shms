package com.joh.shms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_ORDER_DETAIL")
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "I_PRODUCT", nullable = false)
	private Product product;

	@Column(name = "QUANTITY")
	private Integer quantity;

	@Column(name = "paymentAmount")
	private Double paymentAmount;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "PRODUCTION_DATE")
	@Temporal(TemporalType.DATE)
	private Date productionDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EXPIRATION_DATE")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	@Column(name = "SOLD_AMOUNT")
	private Integer soldAmount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Integer getSoldAmount() {
		return soldAmount;
	}

	public void setSoldAmount(Integer soldAmount) {
		this.soldAmount = soldAmount;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", product=" + product + ", quantity=" + quantity + ", paymentAmount="
				+ paymentAmount + ", productionDate=" + productionDate + ", expirationDate=" + expirationDate
				+ ", soldAmount=" + soldAmount + "]";
	}

}