package com.application.customer.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.application.customer.model.Customer;

public interface CustomerService {
    String createCustomer(Customer customer);
    String updateCustomer(Customer customer);
    String deleteCustomer(String id);
    Customer getCustomer(String id);
    List<Customer> getAllCustomer();
    
   
    //search api
    
    //date
   
    List<Customer> searchCustomersByOnboardedDateGreaterThan(Date date);

    List<Customer> searchGreaterCustomer(String query);
    List<Customer> searchGreaterEqualsCustomer(Date date);  
    List<Customer> searchLessCustomer(Date date);  
    List<Customer> searchLessEqualsCustomer(Date date);  

  
    
    //name
    
    List<Customer> searchCustomer(String query);
    
	List<Customer> searchStartsnameCustomer(String query);
	List<Customer> searchEndsnameCustomer(String query);
	List<Customer> searchContainsnameCustomer(String query);
	
	
}
