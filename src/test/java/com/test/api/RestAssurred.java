package com.test.api;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.test.pojo.Datum;
import com.test.pojo.Support;
import com.test.pojo.User;
import com.test.pojo.Users;



import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class RestAssurred {
	
	
	@Test(enabled=true)
	public void jsonSchemaValidation() {
		RestAssured.baseURI = "http://reqres.in/";
		RestAssured.basePath = "api";
		
		given().queryParam("id",2).when().get("/users/").
		then().assertThat()
		.body(matchesJsonSchemaInClasspath("com/test/schema/schema.json"));
	}
	
	@Test(enabled=true)
	public void testPostRequest() {
		
		Response response =  given().header("Content-Type","application/json")
					.body("{\n"  + " \"name\": \"Ritu\",\n" + "   \"job\": \"QA\",\n" + "}")
					.when()
					.post("/users")
					.then()
					.statusCode(201)
					.extract()
					.response();
	
	System.out.println(response.body().prettyPrint());
	
	}
	
	@Test(enabled=true)
	public void testResponseJsonPath() {
		Response response = given().queryParam("page","2")
							.when()
							.get("/users/");
		
		System.out.println(response.asString());
		
		JsonPath js = new JsonPath(response.asString());
		int size = js.getInt("data.size()");
		System.out.println("Array_size" + size);
	
		String firstName = js.getString("data[1].first_name");
		System.out.println("FirstName " + firstName);
	
		System.out.println(response.getBody().asString());
		response.then().statusCode(200)
		.body("total_pages",equalTo(2));
		response.then().header("server","cloudflare");
	
	}
	
	@Test(enabled = true)
	public void testResponseFromPojo() {
		Response response = given().queryParam("page","2").when().get("/users/");
		System.out.println("Response :" + response.asString());
		Users users = response.getBody().as(Users.class);
		System.out.println("Page :" + users.getPage());
	
	
		Support support = users.getSupport();
		System.out.println("Support Text :" + support.getText());
		System.out.println("Support URL :" + support.getUrl());
	
		List<Datum> data = users.getData();
		System.out.println("Data Size :" + data.size());
	
		for(int i=0;i<data.size();i++) {
			if(data.get(i).getId()== 9) {
				System.out.println(data.get(i).getEmail());
				System.out.println(data.get(i).getFirstName());
				System.out.println(data.get(i).getLastName());
				data.get(i).setLastName("Testing");
				break;
			}
			
		}
	
	}
	
	@Test(enabled=true)
	public void postFromPojo() {
		User user = new User();
		user.setName("Ridam");
		user.setJob("QA_Engineer");
		
		Response response = given().header("Content-Type","application/json")
							.body(user)
							.when()
							.post("/users")
							.then()
							.statusCode(201)
							.extract()
							.response();
		
		response.getBody().prettyPrint();
		
		
		
	}

}
