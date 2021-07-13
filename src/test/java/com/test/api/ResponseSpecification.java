package com.test.api;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;

public class ResponseSpecification {
	
	public static io.restassured.specification.ResponseSpecification commonSpec() {
	
	
	ResponseSpecBuilder builder = new ResponseSpecBuilder();
	builder.expectStatusCode(200);
	builder.expectHeader("server","cloudflare");
	builder.expectContentType("application/json");
	
	io.restassured.specification.ResponseSpecification responseSpec = builder.build();
	return responseSpec;
	
	}
	
	
	@Test(enabled=true)
	public static void test_with() {
		String url = "https://reqres.in/api/users?page=2";
				RestAssured
				.when()
				.get(url)
				.then()
				.spec(commonSpec());
		}
	
	@Test(enabled= true)
	public static void test_without() {
	String url = "https://reqres.in/api/users?page=2";
		RestAssured.
		when().get(url)
		.then()
		.spec(commonSpec())
		.body("page",equalTo(2));
	

}
}