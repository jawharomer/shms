package com.joh.shms.service;

import com.joh.shms.model.Doctor;

public interface DoctorService {

	Iterable<Doctor> findAll();

	void delete(int id);

	Doctor update(Doctor doctor);

	Doctor save(Doctor doctor);

	Doctor findOne(int id);

}
