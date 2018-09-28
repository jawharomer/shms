package com.joh.shms.service;

import com.joh.shms.model.Operation;

public interface OperationService {

	Iterable<Operation> findAll();

	Operation save(Operation operation);

	Operation update(Operation operation);

	Operation findOne(int id);

	void delete(int id);

}
