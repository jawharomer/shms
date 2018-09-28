package com.johshms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.PatientVisit;

public interface PatientVisitDAO extends CrudRepository<PatientVisit, Integer> {

}
