package com.apiTestingRa.apiTestingProject;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReqResApis {
	@BeforeSuite
	public void setUp() {
		RestAssured.baseURI = "https://reqres.in";
	}
	
	@Test
	public void getUsersApiAndValidateTheHeaders() {
		RestAssured.basePath = "/api/users?page=2";
		Response resReceived =given().when().get().thenReturn();
		JsonPath booksJson = new io.restassured.path.json.JsonPath(resReceived.asString());
		System.out.println(resReceived.getHeaders());
		List<Object> emailList = booksJson.getList("data.email");
		System.out.println("Email list is :: " + emailList);
		MatcherAssert.assertThat(emailList, hasItems("emma.wong@reqres.in", "janet.weaver@reqres.in"));
		MatcherAssert.assertThat(emailList, hasItem("emma.wong@reqres.in"));
		Headers headerList = resReceived.getHeaders();
        Map<String, String> headerMap = resReceived.getHeaders().asList().stream()
                .collect(Collectors.toMap(header -> header.getName(), header -> header.getValue())); 
        Map<String, String> headerMap1 = new HashMap<String, String>();
        for (Header header : headerList) {
			headerMap1.put(header.getName(), header.getValue());
		}
        
        assertThat(headerMap,hasEntry("Content-Type", "application/json; charset=utf-8"));
        assertThat(headerMap,hasEntry("Connection", "keep-alive"));
        assertThat(headerMap,hasEntry("Transfer-Encoding", "chunked"));
        
        assertThat(headerMap1,hasEntry("Content-Type", "application/json; charset=utf-8"));
        assertThat(headerMap1,hasEntry("Connection", "keep-alive"));
        assertThat(headerMap1,hasEntry("Transfer-Encoding", "chunked"));
	}
	
//	@Test
	public void getUsersApiValidateHeader() {
		RestAssured.basePath = "/api/users?page=2";
		given().when().get().then().header("Content-Type", "application/json; charset=utf-8")
									.header("Connection", "keep-alive")
									.header("Cache-Control", startsWith("max"))
									.body("data[2].avatar",equalTo("https://reqres.in/img/faces/3-image.jpg"));								
	}
	
//	@Test
	public void getUserApi() {
		int userId = 2;
		RestAssured.basePath = "/api/users/"+userId;
//		given().when().get().then().statusCode(200);
//		given().when().get().then().header("Content-Type", "application/json; charset=utf-8");
		Response resReceived = given().when().get().thenReturn();		
		System.out.println("Response is :: " + resReceived.prettyPrint());
		Headers resHeaders = resReceived.getHeaders();
		
//		assertEquals(responseReceived.statusCode(), 200);
	}
	
//	@Test
	public void createUser() {
		RestAssured.basePath = "/api/users";
		String userData = "{\"name\": \"morpheus\",\"job\": \"leader\"}";
		given().body(userData).when().post().then().log().all();
	}
	
//	@Test
	public void createUserWithObject() {
		RestAssured.basePath = "/api/users";
//		String userData = "{\"name\": \"morpheus\",\"job\": \"leader\"}";
		HashMap userData = new HashMap();
		userData.put("name", "morpheus");
		userData.put("job", "leader");		
		given().body(userData).when().post().then().log().all();
	}
	
//	@Test
	public void createUserWithFile() {
		RestAssured.basePath = "/api/users";
//		String userData = "{\"name\": \"morpheus\",\"job\": \"leader\"}";
		File userJsonFile = new File("./src/main/resources/userData.json");		
		given().body(userJsonFile).when().post().then().log().all();
	}
	
//	@Test
	public void updateUserWithFileData() {
		RestAssured.basePath = "/api/users/2";
//		String userData = "{\"name\": \"morpheus\",\"job\": \"leader\"}";
		File userJsonFile = new File("./src/main/resources/userData2.json");		
		given().contentType(ContentType.JSON).body(userJsonFile).when().put().then().body("updatedAt", not(isEmptyOrNullString())).log().all();
	}
	
//	@Test
	public void partialUpdateUserWithFileData() {
		RestAssured.basePath = "/api/users/2";
//		String userData = "{\"name\": \"morpheus\",\"job\": \"leader\"}";
		File userJsonFile = new File("./src/main/resources/userData3.json");		
		given().contentType(ContentType.JSON).body(userJsonFile).when().patch().then().body("updatedAt", not(isEmptyOrNullString())).log().all();
	}
	
//	@Test
	public void deleteUser() {
		RestAssured.basePath = "/api/users/2";
		given().when().delete().then().statusCode(204).log().all();
	}
}
