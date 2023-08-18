Feature: POST Operations

  Background: 
    * url baseUrl
    * def input = input_file
    * header Accept = 'application/json'

  Scenario: Post customer creation by file
    Given path '/customer'
    And def requestBody = input
    And request requestBody
    When method post
    Then status 200
    And eval read('userData.json') == response
    And print response

  Scenario: Post customer creation
    Given path '/customer'
    And request {"id": "A8","name": "Mathan Kumar","email": "mathan@email.com","age": 24,"city": "Salem","state": "Tamil Nadu","country": "India","zipcode": 634500,"onboardedDate": "2005-04-16"}
    When method post
    Then status 200

  Scenario: Post customer already exist
    Given path '/customer'
    And def requestBody = input
    And request requestBody
    When method post
    Then status 500
    And print response

  Scenario: Post invalid information
    Given path '/customer'
    And request {"id": "A9","name": "Mathan Kumar","email": "@email.com","age": 24,"city": "Salem","state": "Tamil Nadu","country": "India","zipcode": 634500,"onboardedDate": "2005-04-16"}
    When method post
    Then status 500
    And print response
    
