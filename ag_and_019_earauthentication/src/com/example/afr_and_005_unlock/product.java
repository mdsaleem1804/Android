package com.example.afr_and_005_unlock;

public class product {
	
	private String name;
    private String gender;
    private String dmy;
    private Long age;
    private String username;
    private String password;

	public product() {
		
	}
	

	public void setName(String xname) {
		this.name = xname;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setGender(String xGender) {
		this.gender = xGender;
	}
	
	public String getGender() {
		return this.gender;
	}

	public void setDmy(String xDmy) {
		this.dmy=xDmy;
	}
	
	public String getDmy() {
		return this.dmy;
	}

	public void setAge(Long xAge) {
		this.age = xAge;
	}
	
	public Long getAge() {
		return this.age;
	}
	
	public void setUsername(String xUsername) {
		this.username = xUsername;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setPassword(String xPassword) {
		this.password=xPassword;
	}
	
	public String getPassword() {
		return this.password;
	}

}