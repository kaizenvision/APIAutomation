package com.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class ApiTest {
	
	@Test
	public void test() {
		
	 given().get("http://localhost:8081/api/v1/employee").then()
	 			.body("employeeName[0]",equalTo("shreyas jos"));
	}
	
	
	@Test
	public void test1() {
		
		baseURI = "http://localhost:8081/api/v1";
		
		given()
			.header("content-type","application/json")
		.when()
			.get("/employee")
		.then()
			.log().all();
		
	}

}
