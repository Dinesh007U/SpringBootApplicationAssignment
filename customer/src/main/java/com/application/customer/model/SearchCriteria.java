package com.application.customer.model;

import java.time.LocalDate;
import java.util.Date;

public class SearchCriteria {
    private String id;
    private String name;
    private LocalDate date;
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
	public LocalDate date() {
		return date;
	}
	public void setOnboardedDate(LocalDate date) {
		this.date = date;
	}

    // Getters and setters for id, name, and onboardedDate
    // ...
//	public boolean isOnboardedDateEmpty() {
//        return onboardedDate == null;
//    }
}
