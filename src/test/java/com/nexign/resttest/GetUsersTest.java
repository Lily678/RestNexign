package com.nexign.resttest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(LogListener.class)
public class GetUsersTest {
    @Test
    public void getUsers() {
        Response res= RestAssured
                .given()
                .baseUri("http://localhost:28080")
                .basePath("/rs/users")
                .when()
                .get().then().statusCode(200)
                .extract().response();
        String response = res.asString();
        Assert.assertEquals(response, "[]", "wrong message");
        System.out.println(response);
    }

    @Listeners(LogListener.class)
    public static class DeleteExistentUser {
        String id;
        @BeforeClass
        public void createUser() {
            Response res= RestAssured
                    .given().header("firstName","Liza")
                    .given().header("lastName","Ivanova")
                    .baseUri("http://localhost:28080")
                    .basePath("/rs/users")
                    .when()
                    .post().then().statusCode(200)
                    .extract().response();
            String response = res.asString();
            Assert.assertNotNull(response, "Error create user");
            System.out.println(response);
            id = TestUtils.extractId(response);
            System.out.println(id);
        }
        @Test
        public void deleteUser(){
            Response res= RestAssured
                    .given()
                    .baseUri("http://localhost:28080")
                    .basePath("/rs/users/"+id)
                    .when()
                    .delete().then().statusCode(200)
                    .extract().response();
            String response = res.asString();
            System.out.println(response);
        }
    }
}
