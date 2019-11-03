package com.nexign.resttest.failed;

import com.nexign.resttest.LogListener;
import com.nexign.resttest.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(LogListener.class)
public class PutUnexistenUser {
    String id ="1";
    @Test
    public void PutUser() {
        Response res = RestAssured
                .given().header("firstName", "Lena")
                .given().header("lastName", "Petrova")
                .baseUri("http://localhost:28080")
                .basePath("/rs/users/" + id)
                .when()
                .put().then().statusCode(404)
                .extract().response();
        String response = res.asString();
        Assert.assertNotNull(response, "Error update user");
        System.out.println(response);
        id = TestUtils.extractId(response);
        System.out.println(id);
    }
}
