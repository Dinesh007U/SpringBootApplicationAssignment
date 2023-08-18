Feature: Delete Test

  Background: 
    * url baseUrl
    * header Accept = 'application/json'
    
    Scenario: Delete API test
    Given path 'customer/A7'
    When method DELETE
    Then status 200
    And print responseStatus
    And print response
    Scenario: Delete API test
    Given path 'customer/A8'
    When method DELETE
    Then status 200
    And print responseStatus
    And print response
    
    
    Scenario: Delete non-existent customer test
    Given path 'customer/A10'
    When method DELETE
    Then status 404
    And print responseStatus
    And print response