package com.test.api;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseGet {

	@Test
	public void test() {
		
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");
		System.out.println(response.statusCode());
		System.out.println(response.asString());
		System.out.println(response.getBody().asString());
		System.out.println(response.statusLine());
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode,200);
		
	
	}

	@Test
	public void test0() {
		
		given().get().then().
		statusCode(200).
		body("data.id[0]",equalTo(7));
	}


}
