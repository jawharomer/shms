package com.joh.shms.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.joh.shms.model.PatientVisit;

public interface PatientVisitService {

	PatientVisit save(PatientVisit patientVisit);

	Iterable<PatientVisit> findAll();

	PatientVisit findOne(int id);

	PatientVisit update(PatientVisit patientVisit);

	void addAttachedFile(int id, MultipartFile file) throws IOException;

}
