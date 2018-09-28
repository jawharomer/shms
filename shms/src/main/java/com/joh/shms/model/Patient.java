package com.joh.shms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.joh.shms.validator.PatientValidation;
import com.joh.shms.validator.PatientVisitValidation;

@Entity

@Table(name = "PATIENTS")
public class Patient {

	@NotNull(groups = { PatientVisitValidation.Insert.class }, message = "patient id is null")
	@Column(name = "I_PATIENT")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank(groups = { PatientValidation.Insert.class }, message = "full name is balnk")
	@Column(name = "FULL_NAME", nullable = false, unique = true)
	private String fullName;

	@Column(name = "PHONE")
	private String phone;

	@NotNull(groups = { PatientValidation.Insert.class }, message = "birthDate is blank")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "MARITAL_STATUS")
	private String maritalStatus;

	@NotNull(groups = { PatientValidation.Insert.class }, message = "gender is null")
	@Min(groups = { PatientValidation.Insert.class }, value = 0)
	@Max(groups = { PatientValidation.Insert.class }, value = 1)
	@Column(name = "GENDER")
	private Integer gender;

	@Column(name = "ENROLLMENT_TIME", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date time;

	@Valid()
	@NotNull(groups = { PatientValidation.Insert.class }, message = "visit reference is null")
	@ManyToOne()
	@JoinColumn(name = "I_VISIT_REFERENCE")
	private VisitReference visitReference;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public VisitReference getVisitReference() {
		return visitReference;
	}

	public void setVisitReference(VisitReference visitReference) {
		this.visitReference = visitReference;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", fullName=" + fullName + ", phone=" + phone + ", birthDate=" + birthDate
				+ ", address=" + address + ", maritalStatus=" + maritalStatus + ", gender=" + gender + ", time=" + time
				+ ", visitReference=" + visitReference + "]";
	}

}
