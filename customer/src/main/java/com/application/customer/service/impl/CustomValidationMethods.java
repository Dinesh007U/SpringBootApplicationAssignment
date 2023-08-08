package com.application.customer.service.impl;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

@Component
public class CustomValidationMethods {

    public void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be blank.");
        }
    }
    
    public void validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        
        if (email == null || email.trim().isEmpty() || !pattern.matcher(email).matches()) {
            throw new ValidationException("Invalid email format");
        }
    }


    public void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new ValidationException("Age should have a range of 0 to 150 values.");
        }
    }

    public void validateCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new ValidationException("City cannot be blank.");
        }
    }

    public void validateState(String state) {
        if (state == null || state.trim().isEmpty()) {
            throw new ValidationException("State cannot be blank.");
        }
    }

    public void validateCountry(String country) {
        if (country == null || country.trim().isEmpty()) {
            throw new ValidationException("Country cannot be blank.");
        }
    }

    public void validateZipcode(Integer zipcode) {
    	 if (zipcode == null || String.valueOf(zipcode).length() < 6) {
    		 throw new ValidationException("Zipcode must be at least 6 characters.");
        }
    }

    public void validateOnboardedDate(LocalDate date) {
        if (date == null ) {
            throw new ValidationException("OnboardedDate is not null.");
        }
       
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Compare the provided onboardedLocalDate with the currentDate
        if (date.isAfter(currentDate)) {
            throw new ValidationException("OnboardedDate should be current date or before the current date.");
        }
    }
}
