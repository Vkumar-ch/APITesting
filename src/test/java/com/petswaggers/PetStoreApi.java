package com.petswaggers;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PetStoreApi {
    @Test
    public void createPetStoreApiUserSuccess(){
        Response response =  given().header("Content-Type","application/json")
                .header("accept","application/json")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \"v12345\",\n" +
                        "  \"firstName\": \"v\",\n" +
                        "  \"lastName\": \"k\",\n" +
                        "  \"email\": \"vin@gmail.com\",\n" +
                        "  \"password\": \"v@123456\",\n" +
                        "  \"phone\": \"1234567890\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}'")
                .when()
                .post("https://petstore.swagger.io/v2/user");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void logIntoPetStoreAppSuccess(){
        Response response = given().header("accept","application/json")
                .queryParam("username","v12345")
                .queryParam("password","v@123456")
                .when()
                .get("https://petstore.swagger.io/v2/user/login?username&password");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void getUserByName(){
        Response response = given().accept("application/json")
                .pathParam("username","v12345")
                .when()
                .get("https://petstore.swagger.io/v2/user/{name}");
        response.prettyPrint();
        response.then().statusCode(200);
        String emailText = response.path("username");
        System.out.println("userEmail : "+emailText);
    }

    @Test
    public void updateExistingUser(){
        Response response = given().accept("application/json")
                .contentType("application/json")
                .pathParam("username","v12345")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \"v12345\",\n" +
                        "  \"firstName\": \"k\",\n" +
                        "  \"lastName\": \"v\",\n" +
                        "  \"email\": \"kumar@gmail.com\",\n" +
                        "  \"password\": \"vin@123\",\n" +
                        "  \"phone\": \"0987654321\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/user/{username}");

        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void deleteUserFromPetStore(){
        Response response = given().accept("application/json")
                .pathParam("username","v12345")
                .when()
                .delete("https://petstore.swagger.io/v2/user/{username}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }



}


