package com.joh.shms.service;

import java.util.List;

import com.joh.shms.model.VisitPayment;

public interface VisitPaymentService {

	List<VisitPayment> findAllByPatientVisitId(int id);

	VisitPayment save(VisitPayment visitPayment);

}
