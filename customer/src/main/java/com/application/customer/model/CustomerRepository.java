package com.application.customer.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	
	List<Customer> findByname(String name);
	
// JPQL query with different operations for onboardedDate
	  @Query("SELECT c FROM Customer c WHERE " + 
	  "c.name = :query OR " + 
	  "c.id = :query OR " + 
	  "CONCAT(c.onboardedDate) LIKE CONCAT('%', :query, '%')") // List<Customer>
	 List<Customer> searchCustomer(@Param("query") String query);
	 
      // "(:onboardedDate is null or c.onboardedDate = :onboardedDate)")

	  @Query("SELECT c FROM Customer c WHERE " +
		        "(:id is null or c.id = :id) and " +
		        "(:name is null or c.name = :name) and" +
		        "(:onboardedDate is null or c.onboardedDate = :onboardedDate)")
		List<Customer> searchEntityCustomers(
		        @Param("id") String id,
		        @Param("name") String name,
		        @Param("onboardedDate") LocalDate date
		);



    
    
    @Query("SELECT c FROM Customer c WHERE " +
            "c.name LIKE CONCAT(:query, '%')")
     List<Customer> searchStartsnameCustomer(@Param("query") String query);
     
     @Query("SELECT c FROM Customer c WHERE " +
            "c.name LIKE CONCAT('%', :query)")
     List<Customer> searchEndsnameCustomer(@Param("query") String query);
     
     @Query("SELECT c FROM Customer c WHERE " +
            "c.name LIKE CONCAT('%', :query, '%')")
     List<Customer> searchContainsnameCustomer(@Param("query") String query);
     
     
  

    // ... other methods ..
    
  

     
//     @Query("SELECT c FROM Customer c WHERE c.onboardedDate > :date")
//     List<Customer> searchCustomersByOnboardedDateGreaterThan(@Param("date") Date date);

     @Query("SELECT c FROM Customer c WHERE c.onboardedDate > :date")
     List<Customer> searchGreaterCustomer(@Param("date") LocalDate date);

     @Query("SELECT c FROM Customer c WHERE c.onboardedDate < :date")
     List<Customer> searchLessCustomer(@Param("date") LocalDate date);
     
     
     @Query("SELECT c FROM Customer c WHERE c.onboardedDate >= :date")
     List<Customer> searchGreaterEqualsCustomer(@Param("date") LocalDate date);
     
     
     @Query("SELECT c FROM Customer c WHERE c.onboardedDate <= :date")
     List<Customer> searchLessEqualsCustomer(@Param("date") LocalDate date);
     

}
