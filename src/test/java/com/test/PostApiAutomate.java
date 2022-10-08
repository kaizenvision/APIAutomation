package com.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utility.ExcelSheetHandle;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
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
	
	
	
	@Test()
	public void postTest() {
		
		baseURI = "http://localhost:3000";
		
		ExcelSheetHandle excelSheetHandle = new ExcelSheetHandle();
		
		Sheet sh = excelSheetHandle.getSheet("user");
		
		JSONObject jsonObject = new JSONObject();
		
		Map<String, Object> data = excelSheetHandle.getExcelSheetData(sh);
		
		jsonObject.putAll(data);
		
		System.out.println(data);
		
		given().accept(ContentType.JSON)
				.header("content-type","application/json")
				.body(jsonObject.toJSONString()).
		when().post("/user")
		.then().statusCode(201);
	}
	
	

}
