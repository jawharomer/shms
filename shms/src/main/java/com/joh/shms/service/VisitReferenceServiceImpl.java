package com.joh.shms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.shms.model.Patient;
import com.joh.shms.model.VisitReference;
import com.johshms.dao.PatientDAO;
import com.johshms.dao.VisitReferenceDAO;

@Service
public class VisitReferenceServiceImpl implements VisitReferenceService {

	@Autowired
	private VisitReferenceDAO visitReferenceDAO;

	@Override
	public Iterable<VisitReference> findAll() {
		return visitReferenceDAO.findAll();
	}

}
