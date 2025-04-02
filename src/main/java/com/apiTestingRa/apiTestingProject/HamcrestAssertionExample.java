package com.apiTestingRa.apiTestingProject;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class HamcrestAssertionExample {
    public String endpoint = "https://restful-booker.herokuapp.com/booking/1";
    
//    @Test
    public void myNumericAssertions() {
    	given().when().get(endpoint).then().log().all().body("totalprice", equalTo(346))
    	.body("totalprice", greaterThan(300))
    	.body("totalprice", greaterThanOrEqualTo(300))
    	.body("totalprice", lessThan(500))
    	.body("totalprice", lessThanOrEqualTo(500));        	
    }
    
    @Test
    public void myStringAssertions() {
    	given().when().get(endpoint).then().log().all()
    	.body("firstname", equalTo("Susan"))
    	.body("firstname", equalToIgnoringCase("suSan"))
    	.body("firstname", startsWith("Su"))
    	.body("firstname", endsWith("an"))
    	.body("firstname", equalToIgnoringWhiteSpace(" Susan "));
    }
}
