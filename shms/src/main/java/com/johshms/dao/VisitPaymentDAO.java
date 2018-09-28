package com.johshms.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.VisitPayment;

public interface VisitPaymentDAO extends CrudRepository<VisitPayment, Integer> {
	List<VisitPayment> findAllByPatientVisitId(int id);
}
