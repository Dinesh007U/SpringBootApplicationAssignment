Background:
 		* url 'http://localhost:8080/customer'
 
 	Scenario:
 		
 		Given path '/search?query=A1'
 		When method get
 		Then status 200