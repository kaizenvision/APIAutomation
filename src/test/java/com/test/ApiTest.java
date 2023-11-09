package com.test;

import org.testng.annotations.Test;

import com.base.BaseClass;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class ApiTest extends BaseClass {
	
	@Test
	public void test() {
		baseURI = "https://reqres.in/api";
		
		given().baseUri(baseURI).when().get("/users").then().assertThat().statusCode(200).log().all();
	 
	}
	
	
	@Test
	public void test1() {
		baseURI = "https://reqres.in/api";
		File file = new File(projectPath+"//src//test//resources//testdata//user.json");
		given().baseUri(baseURI).body(file).when().post("/user").then().assertThat().statusCode(201).log().all();
		
	}

}
