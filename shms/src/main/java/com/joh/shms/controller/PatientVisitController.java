package com.joh.shms.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.shms.domain.model.JsonResponse;
import com.joh.shms.exception.CusDataIntegrityViolationException;
import com.joh.shms.model.AttachedFile;
import com.joh.shms.model.Doctor;
import com.joh.shms.model.Operation;
import com.joh.shms.model.Patient;
import com.joh.shms.model.PatientVisit;
import com.joh.shms.service.AttachedFileService;
import com.joh.shms.service.DoctorService;
import com.joh.shms.service.OperationService;
import com.joh.shms.service.PatienService;
import com.joh.shms.service.PatientVisitService;

@Controller
@RequestMapping(path = "/patientVisits")
public class PatientVisitController {

	private static final Logger logger = Logger.getLogger(PatientVisitController.class);

	@Autowired
	private PatienService patienService;

	@Autowired
	private OperationService operationService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientVisitService patientVisitService;

	@Autowired
	private AttachedFileService attachedFileService;

	@GetMapping()
	public String getAllPatientVisit(Model model) {
		logger.info("getAllPatientVisit->fired");

		Iterable<PatientVisit> patientVisits = patientVisitService.findAll();

		logger.info("patientVisits=" + patientVisits);

		model.addAttribute("patientVisits", patientVisits);

		return "patientVisits";
	}

	@GetMapping(path = "/add/{id}")
	public String getAddingPatientVisit(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getAddingPatientVisit->fired");
		logger.info("patienId=" + id);

		ObjectMapper mapper = new ObjectMapper();

		Patient patient = patienService.findOne(id);

		PatientVisit patientVisit = new PatientVisit();
		patientVisit.setPatient(patient);

		Iterable<Operation> operations = operationService.findAll();

		Iterable<Doctor> doctors = doctorService.findAll();

		model.addAttribute("jsonOperations", mapper.writeValueAsString(operations));

		model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));
		model.addAttribute("jsonDoctors", mapper.writeValueAsString(doctors));
		return "addPatientVisit";
	}

	@PostMapping(path = "/add")
	@ResponseBody
	public JsonResponse addPatientVisit(@RequestBody @Valid PatientVisit patientVisit, BindingResult result) {
		logger.info("addPatientVisit->fired");
		logger.info("patientVisit=" + patientVisit);

		logger.info("erorrs=" + result.getAllErrors());

		if (result.hasErrors()) {
			throw new CusDataIntegrityViolationException("bad input is entered");
		} else {
			patientVisit = patientVisitService.save(patientVisit);

			JsonResponse jsonResponse = new JsonResponse();
			jsonResponse.setStatus(200);
			jsonResponse.setMessage("success");
			jsonResponse.setEtc("" + patientVisit.getId());

			return jsonResponse;
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingPatientVisit(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getEditingPatientVisit->fired");
		logger.info("patientVisitId=" + id);

		ObjectMapper mapper = new ObjectMapper();

		PatientVisit patientVisit = patientVisitService.findOne(id);

		logger.info("patientVisit=" + patientVisit);

		Iterable<Operation> operations = operationService.findAll();

		Iterable<Doctor> doctors = doctorService.findAll();

		model.addAttribute("jsonOperations", mapper.writeValueAsString(operations));

		model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));
		model.addAttribute("jsonDoctors", mapper.writeValueAsString(doctors));
		return "editPatientVisit";
	}

	@PostMapping(path = "/update")
	public String updatePatientVisit(@RequestBody @Valid PatientVisit patientVisit, BindingResult result) {
		logger.info("updatePatientVisit->fired");
		logger.info("patientVisit=" + patientVisit);

		logger.info("erorrs=" + result.getAllErrors());

		if (result.hasErrors()) {
			throw new CusDataIntegrityViolationException("bad input is entered");
		} else {

			patientVisit = patientVisitService.update(patientVisit);

			return "success";
		}

	}

	@PostMapping(path = "/{id}/attachedFile")
	public String addAttachedFile(@PathVariable int id, @RequestParam MultipartFile file) throws IOException {
		logger.info("addAttachedFile->fired");
		logger.info("patientVisitId=" + id);
		logger.info("FileName=" + file.getOriginalFilename());
		if (!file.isEmpty()) {

			patientVisitService.addAttachedFile(id, file);

			return "success";
		} else {
			throw new CusDataIntegrityViolationException("file is empty");
		}

	}

}
