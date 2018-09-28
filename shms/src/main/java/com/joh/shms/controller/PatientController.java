package com.joh.shms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.shms.model.Patient;
import com.joh.shms.model.VisitReference;
import com.joh.shms.service.PatienService;
import com.joh.shms.service.VisitReferenceService;
import com.joh.shms.validator.PatientValidation;

@Controller
@RequestMapping(path = "/patients")
public class PatientController {

	private static final Logger logger = Logger.getLogger(PatientController.class);

	@Autowired
	private PatienService patienService;

	@Autowired
	private VisitReferenceService visitReferenceService;

	@GetMapping
	public String getAllPatinet(Model model) {
		logger.info("getAllPatinet->fired");

		Iterable<Patient> patients = patienService.findAll();
		model.addAttribute("patients", patients);

		return "patients";
	}

	@GetMapping(path = "/add")
	public String getAddingPatinet(Model model) {
		logger.info("getAddingPatinet->fired");

		Iterable<VisitReference> visitReferences = visitReferenceService.findAll();

		model.addAttribute("visitReferences", visitReferences);
		model.addAttribute("patient", new Patient());

		return "patient/addPatient";
	}

	@PostMapping(path = "/add")
	public String addPatient(@RequestBody @Validated(PatientValidation.Insert.class) Patient patient,
			BindingResult result, Model model) {
		logger.info("addPatient->fired");

		logger.info("patient=" + patient);

		logger.info("errors=" + result.getAllErrors());
		if (result.hasErrors()) {

			Iterable<VisitReference> visitReferences = visitReferenceService.findAll();

			model.addAttribute("visitReferences", visitReferences);
			model.addAttribute("patient", patient);

			return "patient/addPatient";

		} else {
			patienService.save(patient);
			return "success";
		}
	}

}
