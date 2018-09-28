package com.johshms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.shms.model.AttachedFile;

public interface AttachedFileDAO extends CrudRepository<AttachedFile, Integer> {

}
