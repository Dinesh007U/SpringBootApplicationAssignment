package com.application.customer.KarateTest;


import com.intuit.karate.junit5.Karate;

public class Runner {
	
	
	@Karate.Test
	Karate POST() {
		return Karate.run("Post.feature").relativeTo(getClass());
	}
	@Karate.Test
	Karate GET() {
		return Karate.run("Search.feature").relativeTo(getClass());
	}
	
	@Karate.Test
	Karate Delete() {
		return Karate.run("Delete.feature").relativeTo(getClass());
	}
	@Karate.Test
	Karate PUT() {
		return Karate.run("Put.feature").relativeTo(getClass());
	}
}
