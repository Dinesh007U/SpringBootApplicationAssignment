package com.application.customer.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.application.customer.exception.BadRequestException;
import com.application.customer.exception.CustomerNotFoundException;
import com.application.customer.exception.DuplicateConflictException;
import com.application.customer.exception.NoValuePresentException;
import com.application.customer.model.Customer;
import com.application.customer.model.CustomerRepository;
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
    public List<Customer> searchCustomer(String query) {
    	 if (query == null || query.isEmpty()) {
             throw new NoValuePresentException("The given input is wrong.");
         }
    	 List<Customer> customers = customerRepository.searchCustomer(query);
    	 if(customers.isEmpty()) {
     		throw new CustomerNotFoundException("No customers found after the provided date.");
     	}
     	return customers;
        
    }

    @Override
    public List<Customer> searchStartsnameCustomer(String query) {
    	  if (query == null || query.isEmpty()) {
              throw new NoValuePresentException("The given input is wrong.");
          }
    	  List<Customer> customers = customerRepository.searchStartsnameCustomer(query);
    	  if(customers.isEmpty()) {
      		throw new CustomerNotFoundException("No customers found after the provided date.");
      	}
      	return customers;
    }
    @Override
    public List<Customer> searchEndsnameCustomer(String query) {
    	if (query == null ) {
            throw new NoValuePresentException("The given input is wrong.");
        }
    	List<Customer> customers = customerRepository.searchEndsnameCustomer(query);
    	if(customers.isEmpty()) {
    		throw new CustomerNotFoundException("No customers found after the provided date.");
    	}
    	return customers;
    }
    @Override
    public List<Customer> searchContainsnameCustomer(String query) {
    	if (query == null ) {
            throw new NoValuePresentException("The given input is wrong.");
        }
    	List<Customer> customers = customerRepository.searchContainsnameCustomer(query);
       // return customerRepository.searchContainsnameCustomer(query);
    	if(customers.isEmpty()) {
    		throw new CustomerNotFoundException("No customers found after the provided date.");
    	}
    	return customers;
    }

    //date
    
    @Override
    public List<Customer> searchGreaterCustomer(LocalDate date) {
    	 if (date == null) {
             throw new NoValuePresentException("The given date is null.");
         }
        //return customerRepository.searchGreaterCustomer(date);
    	  List<Customer> customers = customerRepository.searchGreaterCustomer(date);
          if (customers.isEmpty()) {
              throw new CustomerNotFoundException("No customers found after the provided date.");
          }
          return customers;
    }
    @Override
    public List<Customer> searchGreaterEqualsCustomer(LocalDate date) {
    	 if (date == null) {
             throw new NoValuePresentException("The given date is null.");
         }
        //return customerRepository.searchGreaterEqualsCustomer(date);
    	 List<Customer> customers = customerRepository.searchGreaterEqualsCustomer(date);
         if (customers.isEmpty()) {
             throw new CustomerNotFoundException("No customers found on or after the provided date.");
         }
         return customers;
    }
    @Override
    public List<Customer> searchLessCustomer(LocalDate date) {
    	 if (date == null) {
             throw new NoValuePresentException("The given date is null.");
         }
        //return customerRepository.searchLessCustomer(date);
    	 List<Customer> customers = customerRepository.searchLessCustomer(date);
         if (customers.isEmpty()) {
             throw new CustomerNotFoundException("No customers found before the provided date.");
         }
         return customers;
    }
    @Override
    public List<Customer> searchLessEqualsCustomer(LocalDate date) {
    	if (date == null) {
            throw new NoValuePresentException("The given date is null.");
        }
       // return customerRepository.searchLessEqualsCustomer(date);
        List<Customer> customers = customerRepository.searchLessEqualsCustomer(date);
    	 if (customers.isEmpty()) {
             throw new CustomerNotFoundException("No customers found on or before the provided date.");
         }
         return customers;
    }

    @Override
    public List<Customer> searchEntityCustomers(String id, String name, LocalDate date) {
        // Validate the input data to ensure at least one valid search criteria is provided
        boolean isValidSearch = (id != null && !id.isEmpty()) || (name != null && !name.isEmpty()  || date != null  );

        if (!isValidSearch) {
            throw new BadRequestException("The given entity is wrong");
        }

        // Implement the logic to search customers based on the provided criteria
        List<Customer> customers = customerRepository.searchEntityCustomers(id, name,date);

        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("The given entity's customer details do not exist");
        }

        return customers;
    }

	
}
