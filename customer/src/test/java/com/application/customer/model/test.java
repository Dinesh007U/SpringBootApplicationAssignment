package com.application.customer.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "/resources/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class test {

    @Autowired
    private CustomerRepository customerRepository;

    // Your test methods here

    @Test
    void testFindByName() {
        List<Customer> customerList = customerRepository.findByname("Mock Customer");
        assertThat(customerList).isEmpty();
        // Add your assertions here
    }

    // ... other test methods ...
}
