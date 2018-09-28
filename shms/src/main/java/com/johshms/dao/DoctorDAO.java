package com.johshms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.Doctor;

public interface DoctorDAO extends CrudRepository<Doctor, Integer> {

}
