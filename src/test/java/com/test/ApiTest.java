package com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;

public class ApiTest extends BaseClass {
	
	ValidatableResponse validatableResponse;
	Response response;
	ResponseSpecification res;
	RequestSpecification request;
	
	@Test
	public void test() {
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://reqres.in/api")
				.build();
		 res = new ResponseSpecBuilder().expectStatusCode(200).build();
		 request = given().spec(req);
		
		 response = request.when().get("/users").then().spec(res).extract().response();
		 
		 String getResponse = response.asPrettyString();
		 
		 List<String> name = response.jsonPath().getList("data.email");
		 
		 for(String username : name) {System.out.println(username);}
		 
		 System.out.println(getResponse);
		 
		 if(getResponse.contains("Tracey")) {
			 Assert.assertTrue(true);
		 }
		
	 
	}
	
	
	@Test
	public void test1() {
		baseURI = "https://reqres.in/api";
		File file = new File(projectPath+"//src//test//resources//testdata//user.json");
		validatableResponse  = given().baseUri(baseURI).body(file).when().post("/user").then().log().all();
		
		
		System.out.println(validatableResponse.extract().asPrettyString());
		
		validatableResponse.extract().jsonPath();
		
	}

}
