package com.joh.shms.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.shms.model.Doctor;
import com.joh.shms.model.Operation;
import com.johshms.dao.DoctorDAO;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDAO doctorDAO;

	@Override
	public Iterable<Doctor> findAll() {
		return doctorDAO.findAll();
	}

	@Override
	public Doctor findOne(int id) {
		return doctorDAO.findOne(id);
	}

	@Override
	public Doctor save(Doctor doctor) {
		return doctorDAO.save(doctor);
	}

	@Override
	public Doctor update(Doctor doctor) {
		if (doctorDAO.findOne(doctor.getId()) == null)
			throw new EntityNotFoundException();
		return doctorDAO.save(doctor);
	}

	@Override
	public void delete(int id) {
		doctorDAO.delete(id);
	}

}
