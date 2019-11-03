package com.nexign.resttest.failed;

import com.nexign.resttest.LogListener;
import com.nexign.resttest.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(LogListener.class)
public class PutUserTest {
    String id;

    @BeforeClass
    public void createUser() {
        Response res = RestAssured
                .given().header("firstName", "Liza")
                .given().header("lastName", "Ivanova")
                .baseUri("http://localhost:28080")
                .basePath("/rs/users")
                .when()
                .post().then().statusCode(200)
                .extract().response();
        String response = res.asString();
        Assert.assertNotNull(response, "Error create user");
        id = TestUtils.extractId(response);
        System.out.println(id);
    }
    @Test
    public void PutUser() {
        Response res = RestAssured
                .given().header("firstName", "Lena")
                .given().header("lastName", "Petrova")
                .baseUri("http://localhost:28080")
                .basePath("/rs/users/"+id)
                .when()
                .put().then().statusCode(200)
                .extract().response();
        String response = res.asString();
        id = TestUtils.extractId(response);
        Assert.assertEquals(response, "[{ID="+id+", FIRSTNAME=Lena, LASTNAME=Petrova}]");
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