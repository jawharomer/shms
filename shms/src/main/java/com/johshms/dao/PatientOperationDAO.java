package com.johshms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.PatientOperation;

public interface PatientOperationDAO extends CrudRepository<PatientOperation, Integer> {

}
