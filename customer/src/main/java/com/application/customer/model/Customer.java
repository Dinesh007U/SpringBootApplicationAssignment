package com.application.customer.model;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity

@Table(name="customer_table")
public class Customer {
	@Id
	private String id;
	 //@NotBlank(message = "Name cannot be blank")
    private String name;

   // @Email(message = "Invalid email format")
    private String email;

    //@Min(value = 0, message = "Age cannot be negative")
    //@Max(value = 100, message = "Age cannot be more than 100")
    private int age;

    //@NotBlank(message = "City cannot be blank")
    private String city;

    //@NotBlank(message = "State cannot be blank")
    private String state;

    //@NotBlank(message = "Country cannot be blank")
    private String country;

    //@Size(min = 6, message = "Zipcode must be at least 6 characters")
    private Integer zipcode;
    
    
    @Column(name = "onboarded_Date")
  // @JsonFormat(pattern = "yyyy-MM-dd", shape =Shape.STRING)
    private LocalDate onboardedDate;

	
	
	public Customer(){
		//Default constructor
	}
	
	public Customer(String id, String name, String email, Integer age, String city, String state, String country,
			Integer zipcode,LocalDate onboardedDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.onboardedDate = onboardedDate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getZipcode() {
		return zipcode;
	}
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	public LocalDate getOnboardedDate() {
		return onboardedDate;
	}

	public void setOnboardedDate(LocalDate onboardedDate) {
		this.onboardedDate = onboardedDate;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", zipcode=" + zipcode + ", onboardedDate="
				+ onboardedDate + "]";
	}

	
}
