package com.joh.shms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.shms.model.VisitPayment;
import com.johshms.dao.VisitPaymentDAO;

@Service
public class VisitPaymentServiceImpl implements VisitPaymentService {
	@Autowired
	private VisitPaymentDAO visitPaymentDAO;

	@Override
	public List<VisitPayment> findAllByPatientVisitId(int id) {
		return visitPaymentDAO.findAllByPatientVisitId(id);
	}
	
	@Override
	public VisitPayment save(VisitPayment visitPayment) {
		return visitPaymentDAO.save(visitPayment);
	}

}
