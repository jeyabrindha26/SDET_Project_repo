package com.example.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.springframework.http.HttpStatus;
public class Test1 {	
	@Test
	public void testHello2() {
		String responseString = given()
				.queryParam("name", "Habib")
				.when()
				.get("http://localhost:8989/hello2")
				  .then()
				  .assertThat()
				  .statusCode(HttpStatus.OK.value())
				  .extract()
				  .asString();
		
		Assert.assertEquals(responseString,"Say Hello Habib"); 		
	}
	
	
	
	@Test
	public void testHello1() {
	String responseString1=given()
	//.contentType(ContentType.JSON)
	.pathParam("name", "Hab")
	.when()
	.get("http://localhost:8989/hello1/{name}")
	.then()
	.statusCode(200)
	.extract()
	.asString();
		
		Assert.assertEquals(responseString1,"Hello Hab"); 
	}
	
	@Test
	public void testGetEmpWithBody() {
	
	given()
	.contentType(ContentType.JSON)	
	.when()
	.get("http://localhost:8989/getemp")
	.then()
	.statusCode(200)	
	.body("eno", equalTo(123))
    .body("empName", equalTo("habib")); 
		
	}
	
	
	@Test
	public void testGetEmp() {
	int eno=
	given()
	.contentType(ContentType.JSON)	
	.when()
	.get("http://localhost:8989/getemp")
	.then()
	.statusCode(200)
	.extract()
	.path("eno");	
    System.out.println("===============>"+eno);
	Assert.assertEquals(eno,123); 
		
	}
	
	@Test
	public void testGetEmpJsonPath() {
	
		Response res = RestAssured.given().get("http://localhost:8989/getemp");
		Assert.assertEquals(200, res.getStatusCode());
		String json = res.asString(); 
		JsonPath jp = new JsonPath(json);
		Assert.assertEquals(jp.get("eno").toString(), "123");
		
	}

	@Test
	public void testSaveEmp() {
	String response=given()
	.contentType(ContentType.JSON)
	.body("{\"eno\":234, \"empName\":\"abc\",\"salary\":4545}")
	.when()
	.post("http://localhost:8989/saveemp")
	.then()
	.extract()
	.asString();
	
		/*
		 * Response responseBody= given() .contentType(ContentType.JSON)
		 * .body("{\"eno\":234, \"empName\":\"abc\",\"salary\":4545}") .when()
		 * .post("http://localhost:8989/saveemp");
		 * 
		 * String bodycontent=responseBody.getBody().asString();
		 */
	Assert.assertEquals(response,"Inserted 234"); 		
	}	
	
	@Test
	public void testRemoveEmp() {
		String responseString = delete("http://localhost:8989/removeemp?eno=123")
				  .then()
				  .assertThat()
				  .statusCode(HttpStatus.OK.value())
				  .extract()
				  .asString();
		
		Assert.assertEquals(responseString,"Record Deleted: 123"); 
		
	}
	
	
}
