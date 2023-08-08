package com.application.customer.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.customer.exception.CustomerNotFoundException;
import com.application.customer.exception.ErrorMessage;
import com.application.customer.exception.NoValuePresentException;
import com.application.customer.model.Customer;
import com.application.customer.model.SearchCriteria;
import com.application.customer.service.CustomerService;



@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	
	
	@GetMapping("{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") String id) {
		 Customer customer=customerService.getCustomer(id);
		 return ResponseEntity.ok(customer);
	}

	    // Read all Customer Details
	    @GetMapping
	    public ResponseEntity<List<Customer>> getAllCustomer() {
	        List<Customer> customer = customerService.getAllCustomer();
	        return ResponseEntity.ok(customer);
	    }

	    // Create Customer Details
	    @PostMapping
	    public ResponseEntity<String> createCustomerDetails(@RequestBody Customer customer) {
	        customerService.createCustomer(customer);
	        return ResponseEntity.ok("Customer entry is created successfully");
	    }

	    // Update Customer
	    @PutMapping
	    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
//	        customerService.updateCustomer(customer);
//	        return ResponseEntity.ok("Customer entry is updated successfully");
//	    }
	    	        try {
	    	            customerService.updateCustomer(customer);
	    	            return ResponseEntity.ok("Customer entry is updated successfully");
	    	        } catch (CustomerNotFoundException ex) {
	    	            ErrorMessage errorMessage = new ErrorMessage(404, "No Value Present", ex.getMessage());
	    	            return ResponseEntity.status(404).body(errorMessage);
	    	        }
	    	    }

	    // Delete Customer
	    @DeleteMapping("{id}")
	    public ResponseEntity<String> deleteCustomer(@PathVariable("id") String id) {
	    	 customerService.deleteCustomer(id);
	       	        return ResponseEntity.ok("Customer entry is deleted successfully");
				    }


	
	
	//search api  
	   

	    
	    
	    
	    
	// link -> /customer/search?query={name} or {onboardedLocalDate}
	    	@GetMapping("/search")
	public ResponseEntity<List<Customer>> serachCustomer(@RequestParam("query")String query){
		return ResponseEntity.ok(customerService.searchCustomer(query));
		
	}
	

	
	@GetMapping("/search/starts")
	public ResponseEntity<List<Customer>> searchStartsnameCustomer(@RequestParam("query")String query){
		return ResponseEntity.ok(customerService.searchStartsnameCustomer(query));
		
	}
	@GetMapping("/search/ends")
	public ResponseEntity<List<Customer>> searchEndsnameCustomer(@RequestParam("query")String query){
		return ResponseEntity.ok(customerService.searchEndsnameCustomer(query));
		
	}
	@GetMapping("/search/contains")
	public ResponseEntity<List<Customer>> searchContainsnameCustomer(@RequestParam("query")String query){
		return ResponseEntity.ok(customerService.searchContainsnameCustomer(query));
		
	}
	
	
	//dates
	


	@GetMapping("/search/greater")
    public ResponseEntity<List<Customer>> searchGreaterCustomer(@RequestParam("query") String query) {
		 LocalDate date = LocalDate.parse(query); 
		 return ResponseEntity.ok(customerService.searchGreaterCustomer(date));
    }
	    @GetMapping("/search/less")
	    public ResponseEntity<List<Customer>> searchLessCustomer(@RequestParam("query") String query) {
	        LocalDate date = LocalDate.parse(query);
	        return ResponseEntity.ok(customerService.searchLessCustomer(date));
	    }

	    @GetMapping("/search/ge")
	    public ResponseEntity<List<Customer>> searchGreaterEqualsCustomer(@RequestParam("query") String query) {
	        LocalDate date = LocalDate.parse(query);
	        return ResponseEntity.ok(customerService.searchGreaterEqualsCustomer(date));
	    }

	    @GetMapping("/search/le")
	    public ResponseEntity<List<Customer>> searchLessEqualsCustomer(@RequestParam("query") String query) {
	        LocalDate date = LocalDate.parse(query);
	        return ResponseEntity.ok(customerService.searchLessEqualsCustomer(date));
	    }

		
	    
	    @GetMapping("/searchcustomers")
	    public ResponseEntity<List<Customer>> searchEntityCustomers(@RequestBody SearchCriteria searchCriteria) {
	        List<Customer> customers = customerService.searchEntityCustomers(
	            searchCriteria.getId(),
	            searchCriteria.getName(),
	            searchCriteria.date()
	        );
	        return ResponseEntity.ok(customers);
	    }
	 
	    @ExceptionHandler(NoValuePresentException.class)
	    public ResponseEntity<ErrorMessage> handleNoValuePresentException(NoValuePresentException ex) {
	        ErrorMessage errorMessage = new ErrorMessage(500, "Bad Request", ex.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	    }
	    
	

}
