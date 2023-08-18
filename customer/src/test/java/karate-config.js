function karateTest(){
	var config={
		baseUrl : 'http://localhost:8080',
		output_file : read('output.json'),
		input_file : read('userData.json')
};

var env = karate.env
karate.log("The value of env is: ", env)


karate.configure('connectTimeout',5000)
karate.configure('readTimeout',5000)
return config;
}