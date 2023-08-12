package com.application.customer.KarateTest;

import com.intuit.karate.junit5.Karate;

public class Runner {
	
	@Karate.Test
	Karate sample() {
		return Karate.run("userDet").relativeTo(getClass());
	}
	
	
	
}