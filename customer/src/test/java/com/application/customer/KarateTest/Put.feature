Feature: PUT Operations

  Background: 
    * url baseUrl
    * header Accept = 'application/json'

  Scenario: PUT API test
    Given path 'customer'
    And request {"id": "A5","name": "Mathan Kumar","email": "mathan@email.com","age": 24,"city": "Salem","state": "Tamil Nadu","country": "India","zipcode": 634500,"onboardedDate": "2005-04-16"}
    When method put
    Then status 200
    And print response
    And print responseStatus
    
 Scenario: PUT API test
    Given path 'customer'
    And request {"id": "A5","name": "Mathan Kumar","email": "@email.com","age": 24,"city": "Salem","state": "Tamil Nadu","country": "India","zipcode": 634500,"onboardedDate": "2005-04-16"}
    When method put
    Then status 500
    And print response
    And print responseStatus