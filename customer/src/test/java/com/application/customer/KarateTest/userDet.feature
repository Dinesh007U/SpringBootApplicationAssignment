Feature: Karate Test

	Background:
 		* url 'http://localhost:8080/customer'
 
 	Scenario:
 		Given path '/A1'
 		When method get
 		Then status 200
 		
 		Given path '/A7'
 		When method get
 		Then status 404
 	
 		Given path '/search/contains?query=Vali'
 		When method get
 		Then status 404
 	
        * print "CRUD OPERATIONS"