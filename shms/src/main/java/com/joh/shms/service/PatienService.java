package com.joh.shms.service;

import com.joh.shms.model.Patient;

public interface PatienService {

	Iterable<Patient> findAll();

	Patient save(Patient patient);

	Patient findOne(int id);

}
