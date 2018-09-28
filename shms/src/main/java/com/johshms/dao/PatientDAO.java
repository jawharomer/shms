package com.johshms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.Patient;

public interface PatientDAO extends CrudRepository<Patient, Integer> {

	Iterable<Patient> findAllByOrderByIdDesc();

}
