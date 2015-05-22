package com.cxf.restapis.resource.dog;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DogModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4182615959196847973L;
	
	@JsonIgnore
	private Long dogNumber;
	
	private String dogName;
	
	private String nickName;
	
	private int dogAge;

	public Long getDogNumber() {
		return dogNumber;
	}

	public void setDogNumber(Long dogNumber) {
		this.dogNumber = dogNumber;
	}

	public String getDogName() {
		return dogName;
	}

	public void setDogName(String dogName) {
		this.dogName = dogName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getDogAge() {
		return dogAge;
	}

	public void setDogAge(int dogAge) {
		this.dogAge = dogAge;
	}
	
	

}
