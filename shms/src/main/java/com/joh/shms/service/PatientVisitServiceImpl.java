package com.joh.shms.service;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joh.shms.model.AttachedFile;
import com.joh.shms.model.PatientVisit;
import com.johshms.dao.PatientOperationDAO;
import com.johshms.dao.PatientVisitDAO;

@Service
public class PatientVisitServiceImpl implements PatientVisitService {
	@Autowired
	private PatientVisitDAO patientVisitDAO;

	@Autowired
	private PatientOperationDAO patientOperationDAO;

	@Autowired
	private AttachedFileService attachedFileService;

	@Override
	public PatientVisit findOne(int id) {
		return patientVisitDAO.findOne(id);
	}

	@Override
	public Iterable<PatientVisit> findAll() {
		return patientVisitDAO.findAll();
	}

	@Transactional
	@Override
	public PatientVisit save(PatientVisit patientVisit) {
		final PatientVisit savePV = patientVisitDAO.save(patientVisit);
		patientVisit.getPatientOperations().stream().forEach(e -> {
			e.setPatientVisit(savePV);
			patientOperationDAO.save(e);
		});
		return patientVisit;
	}

	@Transactional
	@Override
	public PatientVisit update(PatientVisit patientVisit) {
		if (patientVisitDAO.findOne(patientVisit.getId()) == null) {
			throw new EntityNotFoundException();
		}

		// Prevent update AttachedFiles
		patientVisit.setAttachedFiles(patientVisitDAO.findOne(patientVisit.getId()).getAttachedFiles());

		final PatientVisit savePV = patientVisitDAO.save(patientVisit);
		patientVisit.getPatientOperations().stream().forEach(e -> {
			e.setPatientVisit(savePV);
			patientOperationDAO.save(e);
		});
		return patientVisit;
	}

	@Transactional
	@Override
	public void addAttachedFile(int id, MultipartFile file) throws IOException {
		AttachedFile attachedFile = attachedFileService.save(file);
		PatientVisit patientVisit = patientVisitDAO.findOne(id);
		patientVisit.getAttachedFiles().add(attachedFile);
		patientVisitDAO.save(patientVisit);
	}

}
