package utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIMethods {
    public APIMethods() {
    }
    RequestSpecification requestSpecification = RestAssured.given();
    public String requestBearerToken(){
        requestSpecification.baseUri("https://accounts.spotify.com");
        requestSpecification.basePath("/api/token");
        String params = String.format("grant_type=%s&client_id=%s&client_secret=%s", "client_credentials", "b3845e6d5e504ff3a6e10ae15e661d8f", "1b8f70e65bfa43eca03eac671f37cc15");
        requestSpecification.header("Content-Type", "application/x-www-form-urlencoded").body(params);

        Response response = requestSpecification.post();
        return response.jsonPath().getString("access_token");
    }
}
