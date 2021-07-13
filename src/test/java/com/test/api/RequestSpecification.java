package com.test.api;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class RequestSpecification {
	
	static io.restassured.specification.RequestSpecification getCommonSpec() {
	RequestSpecBuilder builder = new RequestSpecBuilder();
	builder.setBaseUri("https://reqres.in/");
	builder.setBasePath("api/users");
	
	io.restassured.specification.RequestSpecification requestSpec = builder.build();
	return requestSpec;
	
	}
	
	
	@Test(enabled=true)
	public void testWithoutParam() {
		System.out.println("Without_Param");
		
		Response response = RestAssured
				.given()
				.spec(getCommonSpec())
				.when()
				.get();
		
		response.getBody().prettyPrint();
		
	}
	
	@Test(enabled= true)
	public void testWithParam() {
		System.out.println("With_Param");
		
		Response response = RestAssured
				.given()
				.spec(getCommonSpec())
				.queryParam("page","2")
				.when()
				.get();
		
		response.getBody().prettyPrint();
		
	}
	
	
	
	
}
