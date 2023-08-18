Feature: Karate Test

  Background: 
    * url baseUrl
    * def output = output_file
    * header Accept = 'application/json'
    * print "CRUD OPERATIONS"

  Scenario: Matching response Customer Details.
    Given path 'customer/details'
    When method get
    Then status 200
    #Then match response == output
    And print response
    And print responseStatus
    And print responseTime
    And print responseHeaders
    And print responseCookies

  Scenario: Getting Customer Details by json response.
    Given path 'customer/searchcustomers'
    And request {"id": "A4","name": "Sam Thomas","onboardedDate": "2005-04-13"}
    When method get
    Then status 200
    And print response
    And print responseStatus

  Scenario: Getting Customer Details.
    Given path 'customer/A1'
    When method get
    Then status 200

  Scenario: Customer dose not exist.
    Given path 'customer/A9'
    When method get
    Then status 404

  Scenario: Matching responses.
    Given path 'customer/details'
    When method get
    Then status 200
    Then match response[*].id contains 'A1'

  # With Assertions
  Scenario: Matching data
    Given path 'customer/details'
    When method get
    Then status 200
    And match response[0].email != null
    And assert response.length >= 1
    And match response[1].id == 'A2'
    And match $.[1].name == 'Sarath Kumar'
    * print "SEARCH OPERATIONS"

  Scenario: Query Operation - Find with 'A3'
    Given path 'customer/search'
    And param query = 'A3'
    When method get
    Then status 200

  Scenario: Query Operation - Find with 'Raghu Palani'
    Given path 'customer/search'
    And param query = 'Raghu Palani'
    When method get
    Then status 200

  Scenario: Query Operation - Starts with 'Sam'
    Given path 'customer/search/starts'
    And param query = 'Sam'
    When method get
    Then status 200
    And match response[*].id contains 'A4'
    And def names = karate.map(response, function(x){ return x.name })
    And match each names == 'Sam Thomas'

  Scenario: Query Operation - Ends with 'Kumar'
    Given path 'customer/search/ends'
    And param query = 'Kumar'
    When method get
    Then status 200

  Scenario: Query Operation - Contains with 'Raghu'
    Given path 'customer/search/contains'
    And param query = 'Raghu'
    When method get
    Then status 200

  Scenario: Query Operation - Contains with 'RaM'
    Given path 'customer/search/contains'
    And param query = 'Ram'
    When method get
    Then status 404

  Scenario: Query Operation - Date operations
    Given path 'customer/search/ge'
    And param query = '2005-04-12'
    When method get
    Then status 200

  Scenario: Query Operation - Date operations
    Given path 'customer/search/le'
    And param query = '2005-04-12'
    When method get
    Then status 200

  Scenario: Query Operation - Date operations
    Given path 'customer/search'
    And param query = '2005-04-12'
    When method get
    Then status 200

  Scenario: Query Operation - Date operations
    Given path 'customer/search/greater'
    And param query = '2005-04-12'
    When method get
    Then status 200

  Scenario: Query Operation - Date operations
    Given path 'customer/search/less'
    And param query = '2005-04-12'
    When method get
    Then status 200
