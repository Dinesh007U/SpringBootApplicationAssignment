package com.application.customer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.application.customer.exception.CustomerNotFoundException;
import com.application.customer.model.Customer;
import com.application.customer.model.SearchCriteria;
import com.application.customer.service.CustomerService;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    Customer customerOne;
    Customer customerTwo;

    List<Customer> customerList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        customerOne = new Customer("A1", "Raghu Kumar", "raghu@email.com", 22, "Theni", "Tamil Nadu", "India", 634500, LocalDate.of(2000, 9, 8));
        customerTwo = new Customer("A2", "Prem Kumar", "prem@email.com", 25, "Madurai", "Tamil Nadu", "India", 634500, LocalDate.of(2005, 3, 18));
        customerList.add(customerOne);
        customerList.add(customerTwo);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testGetCustomerDetails() throws Exception {
        when(customerService.getCustomer("A1"))
            .thenReturn(customerOne);

        this.mockMvc.perform(get("/customer/A1"))
            .andDo(print())
            .andExpect(status().isOk());
            
    }
    @Test
    void testGetAllCustomers() throws Exception {
        when(customerService.getAllCustomer())
            .thenReturn(customerList);

        this.mockMvc.perform(get("/customer"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value("A1"))
            .andExpect(jsonPath("$[1].id").value("A2"));
    }

    @Test
    void testCreateCustomer() throws Exception {
        String customerJson = "{\"id\":\"A3\",\"name\":\"John Doe\",\"email\":\"john@email.com\",\"age\":30,\"city\":\"New York\",\"state\":\"NY\",\"country\":\"USA\",\"zipCode\":10001,\"dateOfBirth\":\"1992-08-20\"}";

        this.mockMvc.perform(post("/customer")
                .contentType("application/json")
                .content(customerJson))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void testUpdateCustomer() throws Exception {
        String updatedCustomerJson = "{\"id\":\"A1\",\"name\":\"Updated Name\",\"email\":\"updated@email.com\",\"age\":25,\"city\":\"Updated City\",\"state\":\"Updated State\",\"country\":\"Updated Country\",\"zipCode\":123456,\"dateOfBirth\":\"1995-05-10\"}";

        this.mockMvc.perform(put("/customer")
                .contentType("application/json")
                .content(updatedCustomerJson))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void testDeleteCustomer() throws Exception {
        when(customerService.deleteCustomer("A1"))
            .thenReturn("Success");

        this.mockMvc.perform(delete("/customer/A1"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void testSearchCustomer() throws Exception {
        String query = "Raghu"; // Replace with actual query
        when(customerService.searchCustomer(query))
            .thenReturn(customerList);

        this.mockMvc.perform(get("/customer/search")
                .param("query", query))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value("A1"))
            .andExpect(jsonPath("$[1].id").value("A2"));
    }

    @Test
    void testSearchStartsnameCustomer() throws Exception {
        String query = "Rag"; // Replace with actual query
        when(customerService.searchStartsnameCustomer(query))
            .thenReturn(customerList);

        this.mockMvc.perform(get("/customer/search/starts")
                .param("query", query))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value("A1"))
            .andExpect(jsonPath("$[1].id").value("A2"));
    }

    @Test
    void testSearchEndsnameCustomer() throws Exception {
        String query = "Kumar"; // Replace with actual query
        when(customerService.searchEndsnameCustomer(query))
            .thenReturn(customerList);

        this.mockMvc.perform(get("/customer/search/ends")
                .param("query", query))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value("A1"))
            .andExpect(jsonPath("$[1].id").value("A2"));
    }

    @Test
    void testSearchContainsnameCustomer() throws Exception {
        String query = "Kumar"; // Replace with actual query
        when(customerService.searchContainsnameCustomer(query))
            .thenReturn(customerList);

        this.mockMvc.perform(get("/customer/search/contains")
                .param("query", query))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value("A1"))
            .andExpect(jsonPath("$[1].id").value("A2"));
    }

    @Test
    void testSearchGreaterCustomer() throws Exception {
        String query = "2023-08-15"; // Replace with a valid date
        when(customerService.searchGreaterCustomer(LocalDate.parse(query)))
            .thenReturn(customerList);

        this.mockMvc.perform(get("/customer/search/greater")
                .param("query", query))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value("A1"))
            .andExpect(jsonPath("$[1].id").value("A2"));
    }

    @Test
    void testSearchLessCustomer() throws Exception {
        String query = "2023-08-15"; // Replace with a valid date
        when(customerService.searchLessCustomer(LocalDate.parse(query)))
            .thenReturn(customerList);

        this.mockMvc.perform(get("/customer/search/less")
                .param("query", query))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value("A1"))
            .andExpect(jsonPath("$[1].id").value("A2"));
    }

    // Add more error scenario test cases

    

    
//    @Test
//    void testSearchEntityCustomers() throws Exception {
//        SearchCriteria searchCriteria = new SearchCriteria();
//        searchCriteria.setId("A1");
//        searchCriteria.setName("Raghu Kumar");
//        searchCriteria.setOnboardedDate(LocalDate.of(2000, 9, 8));
//
//        when(customerService.searchEntityCustomers("A1", "Raghu Kumar", LocalDate.of(2000, 9, 8)))
//            .thenReturn(customerList);
//
//        String jsonRequest = "{ \"id\": \"A1\", \"name\": \"Raghu Kumar\", \"date\": \"2000-09-08\" }";
//
//        this.mockMvc.perform(post("/customer/searchcustomers")
//                .contentType("application/json")
//                .content(jsonRequest))
//            .andDo(print())
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$[0].id").value("A1"))
//            .andExpect(jsonPath("$[0].name").value("Raghu Kumar"))
//            .andExpect(jsonPath("$[1].id").value("A2"))
//            .andExpect(jsonPath("$[1].name").value("Prem Kumar"));
//
//           
//
//        // Add this to check if the mock is called with the expected parameters
//        verify(customerService, times(1)).searchEntityCustomers("A1", "Raghu Kumar", LocalDate.of(2000, 9, 8));
//    }



}
