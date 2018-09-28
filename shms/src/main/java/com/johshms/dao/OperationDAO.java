package com.johshms.dao;



import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.Operation;

public interface OperationDAO extends CrudRepository<Operation, Integer> {

}
