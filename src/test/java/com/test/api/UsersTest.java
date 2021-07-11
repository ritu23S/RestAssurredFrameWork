package com.test.api;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.test.pojo.Support;
import com.test.pojo.Users;

public class UsersTest {

	@Test
	public void usersTest() {
		
	Response response = RestAssured.get("https://reqres.in/api/users");	
	Users users = response.getBody().as(Users.class);
	System.out.println("Page::\n" + users.getPage());
	Support support = users.getSupport();
	System.out.println("Support Text::\n" + support.getText());
	System.out.println("Support URL::\n" + support.getUrl());
	}
}
