package com.test.api;

import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class PostTest_1 {
	
	@Test
	public  void postTest(){
	
		JSONObject request = new JSONObject();
		request.put("name","Ritu");
		request.put("job","BA");

		System.out.println(request);
		System.out.println(request.toString());
		
		given().
		body(request.toJSONString()).
		when().
		post("https://reqres.in/api/users").
		then().
		statusCode(201);
	
	
	}
}