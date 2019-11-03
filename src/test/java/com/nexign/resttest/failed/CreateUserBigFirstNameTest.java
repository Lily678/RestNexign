package com.nexign.resttest.failed;

import com.nexign.resttest.LogListener;
import com.nexign.resttest.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(LogListener.class)
public class CreateUserBigFirstNameTest {
    String id;
    @Test
    public void createUserBigFirstName() {
        Response res= RestAssured
                .given().header("firstName","757668666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666677")
                .given().header("lastName","Ivanova")
                .baseUri("http://localhost:28080")
                .basePath("/rs/users")
                .when()
                .post().then().statusCode(500)
                .extract().response();
        String response = res.asString();
        Assert.assertEquals(response, "length is too long", "wrong message");
        id = TestUtils.extractId(response);
        System.out.println(id);
    }
}
