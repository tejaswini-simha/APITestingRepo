package com.apiTestingRa.apiTestingProject;

import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import org.testng.annotations.Test;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;

public class DynamicTestsWithReqResApi {
	String token = "";
	String userId = "";
	
	@Test
	public void getAuthToken() {
		HashMap credData = new HashMap();
		credData.put("email", "eve.holt@reqres.in");
		credData.put("password", "cityslicka");
		ValidatableResponse res = RestAssured.given().body(credData)
					.contentType(ContentType.JSON)
					.when().post("https://reqres.in/api/login")
					.then().log().all().statusCode(200);	
		token = res.extract().jsonPath().getString("token");
		System.out.println("Token is :: " + token);
	}
	
	@Test
	public void createUser() {
		ValidatableResponse res = given()
		.header("Authorization", "Bearer " + token)
		.body(new File("./src/main/resources/userData.json"))
		.when()
		.post("https://reqres.in/api/users")
		.then()
		.log()
		.all()
		.statusCode(greaterThan(200));
		userId = res.extract().jsonPath().getString("id");
		System.out.println("User Id created is :: " + userId);
	}
	
	@Test
	public void updateUser() {
		ValidatableResponse res = given()
		.header("Authorization", "Bearer " + token)
		.body(new File("./src/main/resources/userData2.json"))
		.when()
		.put("https://reqres.in/api/users/" + userId)
		.then()
		.log()
		.all()
		.statusCode(greaterThanOrEqualTo(200));
	}
	
	
	@Test
	public void deleteUser() {
		ValidatableResponse res = given()
		.header("Authorization", "Bearer " + token)
		.when()
		.delete("https://reqres.in/api/users/" + userId)
		.then()
		.log()
		.all()
		.statusCode(greaterThanOrEqualTo(200));
	}
//	@Test
//	public void test() {
//		System.out.println("Hello world!");
//	}
	
};
