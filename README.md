# SpringBootApplicationProject
This project is creating a Spring Boot Applications

Project : 

The objective is for you to learn and then work on an assignment with below requirements
> Create a SpringBoot application with CRUD REST APIs for Customer entity
 - Customer entity should contain following fields - customerId, customerName, customer.address.city, customer.address.state, customer.address.country, customer.address.zipCode, customerEmailAddress, customerOnboardedDate, customerAge feel free to add more fields 
---<done>---




In case if there are validation errors or any internal server error, the response JSON should contain three fields - code, userMessage and errorMessage, the userMessage should be friendly




 - Whenever exception is converted to above mentioned response body to return to API caller the exception with proper error message with full stack trace is always logged



 - DB tables should be defined with PK, Unique Constraint, Indexes, Foreign Key constraints and proper data types, null/non-nullable constraint
---<done>---




> In addition to CRUD operations write a /search API to perform the following searches:

 - requestBody:


{
"filters": [
{
"fieldName": "customerName",
"operator": "EQUALS|STARTS_WITH|ENDS_WITH|CONTAINS",
"value": "John"
},
{
"fieldName": "customerOnboardedDate",
"operator": "EQUALS|GREATER_THAN|LESS_THAN|GREATER_THAN_OR_EQUALS_TO|LESS_THAN_OR_EQUALS_TO",
"value": "Date in ISO8601 format"
}
]
}
 - GREATER_THAN|LESS_THAN|GREATER_THAN_OR_EQUALS_TO|LESS_THAN_OR_EQUALS_TO are supported only for date-time or number fields, if it's used for any other type of fields the 400 bad request error with meaningful message should be returned



> All REST APIs should have proper validations, in case if there are validation errors or any internal server error, the response should contain three fields - code, userMessage and errorMessage, the userMessage should be friendly


> For database interactions the myBatis framework used for insert/update/delete and simple read queries, for complex queries we can use Spring Parameterized Jdbc Template


> Please ensure that exception handling is properly handled in mService, collection objects are used as per our needs and code doesn’t have nay multi threading issues



> Wherever applicable the appropriate Spring annotations should be used to create Spring beans or methods with @Bean annotation should be created



> All the REST APIs, CRUD + Search API, should be automatically tested by writing the unit tests using the frameworks like: Junit, SpringBoot integration testing, Mockito, H2 in-memory DB frameworks


> Code coverage is measured using JaCoCo plugin

> Sonar scan analysis is done on the entire microservice code to ensure there are not major/minor issues or bug from Sonaqube perspective (Eclipse plugin can be used)

> Application should be compiled/built and run using Maven

> For database the MySQL DB can be installed locally

> Java 17 should be used for development and latest SpringBoot starter parent version should be used

> Stretch goals:
 - API specification (Swagger document) should be created for all CRUD + Search REST APIs
 - JMeter load test is created to load test the REST APIs and demonstrate the performance of APIs
 - API should be secured with JWT using Spring security




