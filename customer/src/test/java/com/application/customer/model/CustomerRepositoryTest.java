package com.application.customer.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest

public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    Customer customer;
    
    @BeforeEach
    void setUp() {
    	customer = new Customer("A1",
        "Raghu Kumar",
        "raghu@email.com",
        22,
        "Theni",
        "Tamil Nadu",
        "India",
        634500,
        LocalDate.of(2000, 9, 8));
    	customerRepository.save(customer);
    }
    
    @AfterEach
    void tearDown() {
    	customer= null;
    			customerRepository.deleteAll();
    }

   @Test
   void testFindByName() {
	   List<Customer> customerList = customerRepository.findByname("Raghu Kumar");
       assertThat(customerList.get(0).getId()).isEqualTo(customer.getId());
       assertThat(customerList.get(0).getOnboardedDate()).isEqualTo(customer.getOnboardedDate());
       
   }
   
   @Test
   void testPositiveCrudOperations() {
       // Create
       customer = new Customer("A1","Raghu Kumar", "raghu@email.com",22,"Theni", "Tamil Nadu","India",634500,LocalDate.of(2000, 9, 8));
       customerRepository.save(customer);

       // Read
       Customer retrievedCustomer = customerRepository.findById(customer.getId()).orElse(null);
       assertThat(retrievedCustomer).isNotNull();
       assertThat(retrievedCustomer.getName()).isEqualTo(customer.getName());

       // Update
       retrievedCustomer.setName("Ram Kumar");
       customerRepository.save(retrievedCustomer);
       Customer updatedCustomer = customerRepository.findById(customer.getId()).orElse(null);
       assertThat(updatedCustomer.getName()).isEqualTo("Ram Kumar");

       // Delete
       customerRepository.deleteById(customer.getId());
       Customer deletedCustomer = customerRepository.findById(customer.getId()).orElse(null);
       assertThat(deletedCustomer).isNull();
   }

   @Test
   void testNegativeCrudOperations() {
	   
	   customer = new Customer("A1","Raghu Kumar", "raghu@email.com",22,"Theni", "Tamil Nadu","India",634500,LocalDate.of(2000, 9, 8));
       customerRepository.save(customer);
     
	   
       Optional<Customer> customerList=customerRepository.findById("A2");
       assertThat(customerList.isEmpty()).isTrue();
      
   }
   
  
   
   @Test
   void testPositiveSearchOperations() {
       // Perform search operations and assert results
       List<Customer> searchedCustomers = customerRepository.searchCustomer("Raghu Kumar");
       assertThat(searchedCustomers).isNotEmpty();
       
       // Add more positive search test cases here
   }
   
   @Test
   void testNegativeSearchOperations() {
       // Attempt search operations with invalid data and assert exceptions or empty results

       // Negative search
       List<Customer> searchedCustomers = customerRepository.searchCustomer("Karthi");
       assertThat(searchedCustomers).isEmpty();

       // Add more negative search test cases here
   }
   @Test
   void testPositiveSearchOperations1() {
//       // Positive search: Search by partial name match

       // Positive search: Search by exact onboarded date match
       List<Customer> exactDateMatch = customerRepository.searchCustomer(LocalDate.of(2000, 9, 8).toString());
       assertThat(exactDateMatch).isNotEmpty();

       // Positive search: Search by name starting with "Rag"
       List<Customer> startsWithNameRag = customerRepository.searchStartsnameCustomer("Rag");
       assertThat(startsWithNameRag).isNotEmpty();

       // Positive search: Search by name ending with "Kumar"
       List<Customer> endsWithNameKumar = customerRepository.searchEndsnameCustomer("Kumar");
       assertThat(endsWithNameKumar).isNotEmpty();

       // Positive search: Search by name containing "h"
       List<Customer> containsNameH = customerRepository.searchContainsnameCustomer("h");
       assertThat(containsNameH).isNotEmpty();

       // Positive search: Search by onboarded date greater than a certain date
       LocalDate dateThreshold = LocalDate.of(1995, 1, 1);
       List<Customer> onboardedDateGreaterThan = customerRepository.searchGreaterCustomer(dateThreshold);
       assertThat(onboardedDateGreaterThan).isNotEmpty();

       // Positive search: Search by onboarded date less than a certain date
       LocalDate anotherDateThreshold = LocalDate.of(2020, 1, 1);
       List<Customer> onboardedDateLessThan = customerRepository.searchLessCustomer(anotherDateThreshold);
       assertThat(onboardedDateLessThan).isNotEmpty();

       // ... add more positive search test cases ...
   }

   @Test
   void testNegativeSearchOperations1() {
       // Negative search: Search with invalid query
       List<Customer> invalidQuerySearch = customerRepository.searchCustomer("InvalidQuery");
       assertThat(invalidQuerySearch).isEmpty();

       // Negative search: Search by invalid onboarded date format
       List<Customer> invalidDateSearch = customerRepository.searchCustomer("2021-05-20"); // Invalid format
       assertThat(invalidDateSearch).isEmpty();

       // ... add more negative search test cases ...
   }

}
