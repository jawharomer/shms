package com.joh.shms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.shms.model.Patient;
import com.johshms.dao.PatientDAO;

@Service
public class PatientServiceImpl implements PatienService {

	@Autowired
	private PatientDAO patientDAO;

	@Override
	public Iterable<Patient> findAll() {
		return patientDAO.findAllByOrderByIdDesc();
	}

	@Override
	public Patient save(Patient patient) {
		return patientDAO.save(patient);
	}
	
	

	@Override
	public Patient findOne(int id) {
		return patientDAO.findOne(id);
	}

}
