package main.java;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class APIhandler {
    private APIhandler() {
    }

    private static String getJSONResponse(String url) throws UnirestException {
        String response
                = null;
        response = Unirest.get(url)
                .header("accept", "application/json")
                .asString().getBody();
        return response;

    }

    public static String getRandomCat() {
        String res = null;

        try {
            res = new JSONObject(getJSONResponse("https://aws.random.cat/meow")).getString("file");
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return res;

    }

    public static String getRandomDog() {
        String res = null;

        try {
            res = new JSONObject(getJSONResponse("https://random.dog/woof.json")).getString("url");
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static String getRandomFox() {
        String res = null;

        try {
            res = new JSONObject(getJSONResponse("https://randomfox.ca/floof/")).getString("image");
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return res;
    }


}
