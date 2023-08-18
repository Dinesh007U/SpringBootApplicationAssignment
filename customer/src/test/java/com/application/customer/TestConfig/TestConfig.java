//package com.application.customer.TestConfig;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class TestConfig {
//
//    @Autowired
//    public void loadTestData(DataSource dataSource) {
//        Resource initDataScript = new ClassPathResource("data.sql");
//        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(initDataScript);
//        populator.execute(dataSource);
//    }
//}
