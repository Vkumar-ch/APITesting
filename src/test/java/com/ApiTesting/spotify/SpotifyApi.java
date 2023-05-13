package com.ApiTesting.spotify;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SpotifyApi {

    String UserId;
    String authToken = "Bearer BQB-_cdKHO17NYv7rIL6jIXanspGg-eiPKbNNBUHLnCA1Yp7GeFhEdNim5HTVb-" +
            "B-gYLAA_nv8U0-y63Ld-PkC-UYK3cbBbilz73QoZPcmNJs9mPL0GYbeqi8E_huRs7PEwoYPHRdipdTWMMnl" +
            "FmobOB3QaWUqAgaTu0kjsfHB_hX1sIiOaEwzl098FgRSW9ot4RCiOAwahmNZcqoc0RNCMl60S6tzzio91h5Ev" +
            "74wR7nOIEH_phtY1J5yob2bUR70zQ7giUx0OA1i35Kzl1oA";

    @Test(priority = 0)
    public void getCurrentUsersProfile(){
        Response res = given().accept("application/json")
                .contentType("application/json")
                .header("Authorization",authToken)
                .when()
                .get("https://api.spotify.com/v1/me");

        res.prettyPrint();
        UserId = res.path("id");
        System.out.println("UserId " +UserId);
        res.then().assertThat().statusCode(200);

    }

    @Test(priority = 1)

    public void getUsersProfile(){
        Response res = given().accept("application/json")
                .contentType("application/json")
                .header("Authorization",authToken)
                .when()
                .get("https://api.spotify.com/v1/users/fnkabyir2uy49ism3bacjb9ym");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);


    }

    @Test(priority = 6)

    public void createPlaylist(){
        Response res = given().accept("application/json")
                .contentType("application/json")
                .header("Authorization",authToken)
                .body("{\n" +
                        "    \"name\": \"MY Fav Playlist\",\n" +
                        "    \"description\": \"New playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/users/"+UserId+"/playlists");
        res.prettyPrint();
        res.then().assertThat().statusCode(201);

    }



    @Test(priority = 2)

    public void getUserPlaylist(){
        Response res = given().accept("application/json")
                .contentType("application/json")
                .header("Authorization",authToken)
                .when()
                .get("https://api.spotify.com/v1/users/"+UserId+"/playlists");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);


    }

    @Test(priority = 3)

    public void addItemsToPlaylist(){
       Response res = given().accept("application/json")
                .contentType("application/json")
                .header("Authorization",authToken)
                .body("{\"uris\": [\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\",\"spotify:track:1301WleyT98MSxVHPZCA6M\", \"spotify:episode:512ojhOuo1ktJprKbVcKyQ\"]}")
                .when()
                .post("https://api.spotify.com/v1/playlists/1awBTHMLwQjlu27cJE25UO/tracks");
        res.prettyPrint();
        res.then().assertThat().statusCode(201);
    }


    @Test(priority = 4)

    public void getNewReleases(){
        Response res = given().accept("application/json")
                .contentType("application/json")
                .header("Authorization",authToken)
                .when()
                .get("https://api.spotify.com/v1/browse/new-releases");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);

    }

    @Test(priority = 5)

    public void getAlbums(){
        Response res = given().accept("application/json")
                .contentType("application/json")
                .header("Authorization",authToken)
                .when()
                .get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);

    }

    @Test(priority = 7)

    public void followPlaylist(){
        Response res = given().accept("application/json")
                .contentType("application/json")
                .header("Authorization",authToken)
                .body("{\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/2af3zLTBA82epqQlFpXUWN/followers");
                res.prettyPrint();
                res.then().assertThat().statusCode(200);

    }


    @Test(priority = 8)
    public void searchForItem(){

        Response res = given().accept("application/json")
                .contentType("application/json")
                .header("Authorization",authToken)
                .queryParam("q","remaster%20track:Doxy%20artist:Miles%20Davis")
                .pathParam("playlist","2af3zLTBA82epqQlFpXUWN")
                .when()
                .get("https://api.spotify.com/v1/search");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);




    }





}


