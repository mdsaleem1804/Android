package com.prosav;

import android.app.Application;

public class GlobalClass extends Application {

	private String p_fullname;
	private Integer p_age;
	private String p_mobileno;
	private String p_landlineno;
	private String p_email;
	private String p_address;
	private String p_medicalhistory;
	private String p_clinicalnotes;
	private String p_username;
	private String p_status;
	private String p_signup_ver_status;

	private String d_fullname = "";
	private String d_mobileno = "";
	private String d_username = "";
	private Integer d_age = 0;
	private String d_landlineno = "";
	private String d_email = "";
	private String d_address = "";
	private String d_specialist = "";
	private String d_signup_ver_status="0";

	public String getDFullName() {

		return d_fullname;
	}

	public void setDFullName(String xDFullName) {

		d_fullname = xDFullName;

	}

	public String getDMobileno() {

		return d_mobileno;
	}

	public void setDMobileno(String xDMobileno) {

		d_mobileno = xDMobileno;
	}

	public String getDUserName() {

		return d_username;
	}

	public void setDUserName(String xDUsername) {

		d_username = xDUsername;
	}

	public Integer getDAge() {

		return d_age;
	}

	public void setDAge(Integer xDAge) {

		d_age = xDAge;
	}

	public String getDLandlineno() {

		return d_landlineno;
	}

	public void setDLandlineno(String xDLandlineno) {

		d_landlineno = xDLandlineno;
	}

	public String getDEmail() {

		return d_email;
	}

	public void setDEmail(String xDEmail) {

		d_email = xDEmail;
	}

	public String getDAddress() {

		return d_address;
	}

	public void setDAddress(String xDAddress) {

		d_address = xDAddress;
	}

	public String getDSpecialist() {

		return d_specialist;
	}

	public void setDSpecialist(String xDSpecialist) {

		d_specialist = xDSpecialist;
	}

	public String getDSignUp_Ver_Status() {

		return d_signup_ver_status;
	}

	public void setDSignUp_Ver_Status(String xDSignupVerStatus) {

		d_signup_ver_status = xDSignupVerStatus;
	}

	
	
	
	
	
	public String getFullName() {

		return p_fullname;
	}

	public void setFullName(String xFullName) {

		p_fullname = xFullName;

	}

	public Integer getAge() {

		return p_age;
	}

	public void setAge(Integer xAge) {

		p_age = xAge;
	}

	public String getMobileno() {

		return p_mobileno;
	}

	public void setMobileno(String xMobileno) {

		p_mobileno = xMobileno;
	}

	public String getLandlineno() {

		return p_landlineno;
	}

	public void setLandlineno(String xLandlineno) {

		p_landlineno = xLandlineno;
	}

	public String getEmail() {

		return p_email;
	}

	public void setEmail(String xEmail) {

		p_email = xEmail;
	}

	public String getAddress() {

		return p_address;
	}

	public void setAddress(String xAddress) {

		p_address = xAddress;
	}

	public String getClinicalnotes() {

		return p_clinicalnotes;
	}

	public void setClinicalnotes(String xClinicalnotes) {

		p_clinicalnotes = xClinicalnotes;
	}

	public String getMedicalhistory() {

		return p_medicalhistory;
	}

	public void setMedicalhistory(String xMedicalhistory) {

		p_medicalhistory = xMedicalhistory;
	}

	public String getUserName() {

		return p_username;
	}

	public void setUserName(String xUsername) {

		p_username = xUsername;
	}

	public String getStatus() {

		return p_status;
	}

	public void setStatus(String xStatus) {

		p_status = xStatus;
	}

	public String getSignUp_Ver_Status() {

		return p_signup_ver_status;
	}

	public void setSignUp_Ver_Status(String xSignupVerStatus) {

		p_signup_ver_status = xSignupVerStatus;
	}

}