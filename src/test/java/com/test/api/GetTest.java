package com.test.api;

import org.testng.annotations.Test;

import com.test.pojo.Support;
import com.test.pojo.Users;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.given;
public class GetTest {
	
	@Test
	
	public void test1() {
		
		
		given().get("https://reqres.in/api/users?page=2").then().
		statusCode(200).
		body("data.id[1]",equalTo(8)).
		body("data.first_name[1]",equalTo("Lindsay")).
		log().all();
	
	}
	
	

}
