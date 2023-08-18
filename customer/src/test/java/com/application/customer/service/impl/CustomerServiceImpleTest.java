package com.application.customer.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.application.customer.exception.CustomerNotFoundException;
import com.application.customer.exception.DuplicateConflictException;
import com.application.customer.model.Customer;
import com.application.customer.model.CustomerRepository;
import com.application.customer.service.CustomerService;

class CustomerServiceImpleTest {

	@Mock
	private CustomerRepository customerRepository;
    private CustomValidationMethods customValidationMethods; // Mocked instance
	private CustomerService customerService;
	AutoCloseable autoCloseable;
	Customer customer;
	
	
	
	@BeforeEach
	void setUp() {
	    autoCloseable = MockitoAnnotations.openMocks(this);
	    customValidationMethods = mock(CustomValidationMethods.class); // Mock the instance
	    customerService = new CustomerServiceImple(customerRepository, customValidationMethods);
	    customer = new Customer("A1", "Raghu Kumar", "raghu@email.com", 22, "Theni", "Tamil Nadu", "India", 634500, LocalDate.of(2000, 9, 8));
	}

	
	@AfterEach
	void tearDown() throws Exception{
		autoCloseable.close(); 
	}
	 @Test
	    public void testCreateCustomer_Input_Success() {
	        when(customerRepository.existsById(anyString())).thenReturn(false);

	        String result = customerService.createCustomer(customer);

	        verify(customerRepository, times(1)).save(customer);
	        assertThat(result).isEqualTo("Success");
	    }
	  
	    @Test
	    public void testCreateCustomer_DuplicateId_Exception() {
	        when(customerRepository.existsById(anyString())).thenReturn(true);

	        assertThrows(DuplicateConflictException.class, () -> customerService.createCustomer(customer));
	    }

	    @Test
	    public void testGetCustomer_Id_Success() {
	        String customerId = "A1";
	        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

	        Customer result = customerService.getCustomer(customerId);

	        Assertions.assertEquals(customer, result);
	    }

	    @Test
	    public void testGetCustomer_NonexistentId_Exception() {
	        String nonexistentId = "A5";
	        when(customerRepository.findById(nonexistentId)).thenReturn(Optional.empty());

	        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomer(nonexistentId));
	    }
	    
	
	
	    @Test
	    public void testUpdateCustomer_Input_Success() {
	        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customer));

	        Customer updatedCustomer = new Customer("A1", "Updated Name", "updated@email.com", 25, "Updated City", "Updated State", "Updated Country", 123456, LocalDate.of(1995, 5, 10));
	        String result = customerService.updateCustomer(updatedCustomer);

	        verify(customerRepository, times(1)).save(updatedCustomer);
	        Assertions.assertEquals("Success", result);
	    }


	    @Test
	    public void testUpdateCustomer_tId_Exception() {
	        when(customerRepository.findById(anyString())).thenReturn(Optional.empty());

	        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(customer));
	    }

	    @Test
	    public void testDeleteCustomer_Id_Success() {
	        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customer));

	        String result = customerService.deleteCustomer("A1");

	        verify(customerRepository, times(1)).deleteById("A1");
	        assertEquals("Success", result);
	    }

	    @Test
	    public void testDeleteCustomer_NonexistentId_Exception() {
	        when(customerRepository.findById(anyString())).thenReturn(Optional.empty());

	        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer("NonexistentId"));
	    }
	    @Test
	    public void testSearchStartsnameCustomer_Query_Success() {
	        String query = "Raghu"; // Replace this with the actual starting string
	        when(customerRepository.searchStartsnameCustomer(query)).thenReturn(List.of(customer));

	        List<Customer> result = customerService.searchStartsnameCustomer(query);

	        verify(customerRepository, times(1)).searchStartsnameCustomer(query);
	        assertThat(result).containsExactly(customer);
	    }
	 
	    @Test
	    public void testSearchEndsnameCustomer_Query_Success() {
	        String query = "Kumar"; // Replace this with the actual ending string
	        when(customerRepository.searchEndsnameCustomer(query)).thenReturn(List.of(customer));

	        List<Customer> result = customerService.searchEndsnameCustomer(query);

	        verify(customerRepository, times(1)).searchEndsnameCustomer(query);
	        assertThat(result).containsExactly(customer);
	    }

	    @Test
	    public void testSearchContainsnameCustomer_Query_Success() {
	        String query = "g"; // Replace this with the actual substring
	        when(customerRepository.searchContainsnameCustomer(query)).thenReturn(List.of(customer));

	        List<Customer> result = customerService.searchContainsnameCustomer(query);

	        verify(customerRepository, times(1)).searchContainsnameCustomer(query);
	        assertThat(result).containsExactly(customer);
	    }

	    @Test
	    public void testSearchGreaterCustomer_Date_Success() {
	        LocalDate date = LocalDate.of(1990, 1, 1); // Replace this with the actual date
	        when(customerRepository.searchGreaterCustomer(date)).thenReturn(List.of(customer));

	        List<Customer> result = customerService.searchGreaterCustomer(date);

	        verify(customerRepository, times(1)).searchGreaterCustomer(date);
	        assertThat(result).containsExactly(customer);
	    }

	    @Test
	    public void testSearchGreaterEqualsCustomer_Date_Success() {
	        LocalDate date = LocalDate.of(1990, 1, 1); // Replace this with the actual date
	        when(customerRepository.searchGreaterEqualsCustomer(date)).thenReturn(List.of(customer));

	        List<Customer> result = customerService.searchGreaterEqualsCustomer(date);

	        verify(customerRepository, times(1)).searchGreaterEqualsCustomer(date);
	        assertThat(result).containsExactly(customer);
	    }

	    @Test
	    public void testSearchLessCustomer_Date_Success() {
	        LocalDate date = LocalDate.of(2022, 1, 1); // Replace this with the actual date
	        when(customerRepository.searchLessCustomer(date)).thenReturn(List.of(customer));

	        List<Customer> result = customerService.searchLessCustomer(date);

	        verify(customerRepository, times(1)).searchLessCustomer(date);
	        assertThat(result).containsExactly(customer);
	    }

	    @Test
	    public void testSearchLessEqualsCustomer_Date_Success() {
	        LocalDate date = LocalDate.of(2022, 1, 1); // Replace this with the actual date
	        when(customerRepository.searchLessEqualsCustomer(date)).thenReturn(List.of(customer));

	        List<Customer> result = customerService.searchLessEqualsCustomer(date);

	        verify(customerRepository, times(1)).searchLessEqualsCustomer(date);
	        assertThat(result).containsExactly(customer);
	    }

}
