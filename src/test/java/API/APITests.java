package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.Test;
import utils.APIMethods;

public class APITests {
    RequestSpecification requestSpecification = RestAssured.given();
    APIMethods methods = new APIMethods();

    @Test
    public void testGetPlaylistInfo(){
        String playListId = "37i9dQZF1DZ06evO1TeWuG";
        String bearerToken = methods.requestBearerToken();
        requestSpecification.baseUri("https://api.spotify.com");
        requestSpecification.basePath("/v1/playlists/" + playListId);
        requestSpecification.header("Authorization", "Bearer "+ bearerToken);

        Response response = requestSpecification.get();
        System.out.println("The name of the playlist is: " + response.jsonPath().getString("name"));
        JsonObject jsonResponse = JsonParser.parseString(response.getBody().asString()).getAsJsonObject();
        JsonObject tracks = jsonResponse.getAsJsonObject("tracks");
        JsonArray items = tracks.getAsJsonArray("items");
        System.out.println("The amount of tracks are: "+ items.size());
    }

    @Test
    public void testGetTrackInfo(){
        String trackId = "4Pl9kwSv46QMwk4MtTttBd";
        String bearerToken = methods.requestBearerToken();
        requestSpecification.baseUri("https://api.spotify.com");
        requestSpecification.basePath("/v1/tracks/" + trackId);
        requestSpecification.header("Authorization", "Bearer "+ bearerToken);

        Response response = requestSpecification.get();
        System.out.println("The name of the track is: " + response.jsonPath().getString("name"));
        JsonObject jsonResponse = JsonParser.parseString(response.getBody().asString()).getAsJsonObject();
        JsonArray artists = jsonResponse.getAsJsonObject("album").getAsJsonArray("artists");
        for (int i = 0; i < artists.size(); i++) {
            JsonObject artist = artists.get(i).getAsJsonObject();
            String artistName = artist.get("name").getAsString();
            System.out.println("Artist " + (i + 1) + ": " + artistName);
        }
    }

    @Test
    public void testGetTopTracksForArtist(){
        String artistId = "3ghRXw2nUEH2THaL82hw8R";
        String bearerToken = methods.requestBearerToken();
        requestSpecification.baseUri("https://api.spotify.com");
        requestSpecification.basePath("/v1/artists/" + artistId +"/top-tracks");
        requestSpecification.header("Authorization", "Bearer "+ bearerToken);

        Response response = requestSpecification.get();
        JsonObject jsonResponse = JsonParser.parseString(response.getBody().asString()).getAsJsonObject();
        JsonArray tracks = jsonResponse.getAsJsonArray("tracks");
        for (int i = 0; i < tracks.size(); i++) {
            JsonObject track = tracks.get(i).getAsJsonObject();
            String trackName = track.get("name").getAsString();
            System.out.println("Track " + (i + 1) + " Name: " + trackName);
        }
    }
}
