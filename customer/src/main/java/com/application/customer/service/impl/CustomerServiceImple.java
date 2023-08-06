package com.application.customer.service.impl;

import java.sql.Date;
import java.util.List;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.application.customer.exception.CustomerNotFoundException;
import com.application.customer.exception.DuplicateConflictException;
import com.application.customer.model.Customer;
import com.application.customer.model.CustomerRepository;
import com.application.customer.model.SearchCriteria;
import com.application.customer.service.CustomerService;

@Service
@Validated
public class CustomerServiceImple implements CustomerService {

    CustomerRepository customerRepository;
	CustomValidationMethods customValidationMethods;

    public CustomerServiceImple(CustomerRepository customerRepository,CustomValidationMethods customValidationMethods) {
        this.customerRepository = customerRepository;
        this.customValidationMethods = customValidationMethods;
    }
    
    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

	@Override
	public Customer getCustomer(String id) {
	    return customerRepository.findById(id)
        .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));

		}
	@Override
	public String createCustomer(Customer customer) {

		 String id = customer.getId();
	        if (customerRepository.existsById(id)) {
	            // Customer with the given ID already exists, throw the custom exception
	            throw new DuplicateConflictException("Customer ID " + id + " already exists.");
	        }

	        // Performing other validations here using javax.validation annotations
	        // Perform validations using the customValidationMethods
	        customValidationMethods.validateName(customer.getName());
	        customValidationMethods.validateEmail(customer.getEmail());
	        customValidationMethods.validateAge(customer.getAge());
	        customValidationMethods.validateCity(customer.getCity());
	        customValidationMethods.validateState(customer.getState());
	        customValidationMethods.validateCountry(customer.getCountry());
	        customValidationMethods.validateZipcode(customer.getZipcode());
	        customValidationMethods.validateOnboardedDate(customer.getOnboardedDate());
	        
	        // If all validations pass, save the customer
	        customerRepository.save(customer);
	        return "Success";
	    }


	@Override
	public String updateCustomer(Customer customer) {
		//customerRepository.save(customer);
		//return "Success";
		 String id = customer.getId();
	        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);

	        if (existingCustomerOptional.isPresent()) {
	            // Customer with the given ID exists, so perform the update
	            customerRepository.save(customer);
	            return "Success";
	        } else {
	            // Customer with the given ID does not exist, throw the custom exception
	            throw new CustomerNotFoundException("Customer with ID " + id + " not found for update");
	        }
	    }
	

@Override
	public String deleteCustomer(String id) {
//		customerRepository.deleteById(id);
//		return "Success";
	Optional<Customer> existingCustomerOptional = customerRepository.findById(id);
	if (existingCustomerOptional.isPresent()) {
        // Customer with the given ID exists, so perform the delete
		customerRepository.deleteById(id);
        return "Success";
    } else {
        // Customer with the given ID does not exist, throw the custom exception
        throw new CustomerNotFoundException("Customer with ID " + id + " not found for deletion");
    }
	
	}
    
    //search
@Override
public List<Customer> searchCriteriaCustomer(SearchCriteria searchCriteria) {
    String id = searchCriteria.getId();
    String name = searchCriteria.getName();
    Date onboardedDate = searchCriteria.getOnboardedDate();

    // Implement the logic to search customers based on the provided criteria
    // Use the customerRepository methods accordingly
   
    return customerRepository.searchCustomersByCriteria(id, name, onboardedDate);

}

    @Override
    public List<Customer> searchCustomer(String query) {
        return customerRepository.searchCustomer(query);
    }

    @Override
    public List<Customer> searchStartsnameCustomer(String query) {
        return customerRepository.searchStartsnameCustomer(query);
    }
    @Override
    public List<Customer> searchEndsnameCustomer(String query) {
        return customerRepository.searchEndsnameCustomer(query);
    }
    @Override
    public List<Customer> searchContainsnameCustomer(String query) {
        return customerRepository.searchContainsnameCustomer(query);
    }

    @Override
    public List<Customer> searchGreaterCustomer(String query) {
        Date date = Date.valueOf(query);
        return customerRepository.searchGreaterCustomer(date);
    }
    
    @Override
    public List<Customer> searchCustomersByOnboardedDateGreaterThan(Date date) {
        return customerRepository.searchCustomersByOnboardedDateGreaterThan(date);
    }

    @Override
    public List<Customer> searchGreaterEqualsCustomer(Date date) {
        return customerRepository.searchGreaterEqualsCustomer(date);
    }
    @Override
    public List<Customer> searchLessCustomer(Date date) {
        return customerRepository.searchLessCustomer(date);
    }
    @Override
    public List<Customer> searchLessEqualsCustomer(Date date) {
        return customerRepository.searchLessEqualsCustomer(date);
    }



	
}
