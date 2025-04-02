package com.apiTestingRa.apiTestingProject;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class AuthExamples {

//	@Test
	public void basicAuthExample() {
		Response resReceived =RestAssured.given()
				.auth().basic("postman", "password")
				.get("https://postman-echo.com/basic-auth")
				.thenReturn();
		assertEquals(resReceived.jsonPath().getBoolean("authenticated"), true);
	}
	
//	@Test
//	public void basicAuthExample2() {
//		Response resReceived =RestAssured.given()
//				.auth().basic("postman", "password")
//				.post("https://postman-echo.com/post")
//				.then().statusCode(20)
//		System.out.println(resReceived.asPrettyString());
//	}
//	
//	@Test
	public void apiKeyAuthExample() {
		RestAssured.given()
				.queryParam("APPID", "9bc1f96a4508c0ce1cc8aa6ab737a566")
				.queryParam("q","Bengaluru,India")
				.when().get("https://api.openweathermap.org/data/2.5/weather")
				.then().statusCode(200)
				.body("name", equalTo("Bengaluru"));
	}
	
//	@Test
	public void oauth2Example() {
		RestAssured.given()
				.auth().oauth2("0534804ce68e8fce2a62bc597176f38b029d1229")
				.when().get("https://api.imgur.com/3/account/tejsapi")
				.then().statusCode(200)
				.body("data.url", containsString("tejsapi"));
	}
	
	@Test
	public void bearerTokenExample() {
		RestAssured.given()
		.header("Authorization", "Bearer 0534804ce68e8fce2a62bc597176f38b029d1229")
				.when().get("https://api.imgur.com/3/account/me/images")
				.then().statusCode(200)
				.body("data.type", hasItem("image/jpeg"));
	}
}
