package com.application.customer.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;



import com.application.customer.model.Customer;
import com.application.customer.model.CustomerRepository;
import com.application.customer.service.CustomerService;

@Service
public class CustomerServiceImple implements CustomerService {

    CustomerRepository customerRepository;

    public CustomerServiceImple(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

	@Override
	public Customer getCustomer(String id) {
    	return customerRepository.findById(id).get();
		}
	@Override
	public String createCustomer(Customer customer) {
		customerRepository.save(customer);
		return "Success";
	}

	@Override
	public String updateCustomer(Customer customer) {
		customerRepository.save(customer);
		return "Success";
	}

@Override
	public String deleteCustomer(String id) {
		customerRepository.deleteById(id);
		return "Success";
	}
    
    //search

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
