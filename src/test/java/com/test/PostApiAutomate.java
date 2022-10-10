package com.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playload.Department;
import com.playload.PlayLoad;
import com.playload.User;
import com.utility.ExcelSheetHandle;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.json.simple.JSONObject;

public class PostApiAutomate{
	
	@Test
	public void getTest() {
		
		baseURI = "http://localhost:3000";
		
		given().accept(ContentType.JSON)
			.header("content-type","application/json")
		.when().
				get("/user")
		.then().statusCode(200).log().all();
		
	}
	
	@DataProvider(name = "userdata")
	public Object[][] usersData() {
		ExcelSheetHandle excelSheetHandle = new ExcelSheetHandle();
		Sheet sh = excelSheetHandle.getSheet("user");
		return excelSheetHandle.getExcelSheetData(sh);
	}
	
	
	
	@Test(dataProvider = "userdata")
	public void postTest(HashMap<String, Object> data) {
		baseURI = "http://localhost:3000";
		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(data);
		given().accept(ContentType.JSON)
				.header("content-type","application/json")
				.body(jsonObject.toJSONString()).
		when().post("/user")
		.then().statusCode(201);
	}
	
	@Test
	public void testWithPlayLoadClasses() throws JsonProcessingException {
		
		User user = new User(12,"Rohan", "BFS");
		
		ObjectMapper mapper = new ObjectMapper();
		
		String userData = mapper.writeValueAsString(user);
		
		
		baseURI = "http://localhost:3000";
		
		given().accept(ContentType.JSON)
				.header("content-type","application/json")
				.body(userData).
		when().post("/user")
		.then().statusCode(201);
		
	}
	@Test
	public void getReuestWithPojo() throws JsonMappingException, JsonProcessingException {
		baseURI = "http://localhost:3000";
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.readValue(given().accept(ContentType.JSON)
				.header("content-type","application/json")
				.when().
						get("/user").toString(), PlayLoad.class);
		
		
	}
	
	

}
