package com.nexign.resttest.failed;

import com.nexign.resttest.LogListener;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(LogListener.class)
public class DeleteUnexistenUserTest {
    String id="1";
    @Test
    public void deleteUser(){
        Response res= RestAssured
                .given()
                .baseUri("http://localhost:28080")
                .basePath("/rs/users/"+id)
                .when()
                .delete().then().statusCode(404)
                .extract().response();
        String response = res.asString();
        Assert.assertEquals(response, "", "there are no users");
        System.out.println(response);
    }
    
}
