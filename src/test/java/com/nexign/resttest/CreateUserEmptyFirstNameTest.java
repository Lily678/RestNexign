package com.nexign.resttest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(LogListener.class)
public class CreateUserEmptyFirstNameTest {
    String id;
    @Test
    public void createUserEmptyFirstName() {
        Response res= RestAssured
                .given().header("firstName","")
                .given().header("lastName","Ivanova")
                .baseUri("http://localhost:28080")
                .basePath("/rs/users")
                .when()
                .post().then().statusCode(200)
                .extract().response();
        String response = res.asString();
        id = TestUtils.extractId(response);
        Assert.assertEquals(response, "[{ID="+id+", FIRSTNAME=, LASTNAME=Ivanova}]");
        System.out.println(id);
    }

    @AfterTest
    public void deleteUser(){
        Response res= RestAssured
                .given()
                .baseUri("http://localhost:28080")
                .basePath("/rs/users/"+id)
                .when()
                .delete().then().statusCode(200)
                .extract().response();
        String response = res.asString();
        Assert.assertEquals(response, "", "wrong message");
        System.out.println(response);
    }
}
