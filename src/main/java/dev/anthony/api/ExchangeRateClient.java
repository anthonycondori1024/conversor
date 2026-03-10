package dev.anthony.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ExchangeRateClient {
    private final String apiKey;
    private final HttpClient httpClient;

    public ExchangeRateClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.apiKey = loadApiKey();
    }

    private String loadApiKey() {
        try (var input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Missing config.properties! Please create one in src/main/resources");
            }
            Properties prop = new Properties();
            prop.load(input);
            String key = prop.getProperty("API_KEY");
            if (key == null || key.isBlank() || key.contains("your_key_here")) {
                throw new RuntimeException("API_KEY is empty in config.properties");
            }
            return key;
        } catch (Exception e) {
            throw new RuntimeException("Could not load API Key from config.properties", e);
        }
    }

    public String fetchSupportedCodes() throws IOException, InterruptedException {
        return sendRequest("https://v6.exchangerate-api.com/v6/" + apiKey + "/codes");
    }

    public double fetchRate(String base, String target) throws IOException, InterruptedException {
        String response = sendRequest("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" +
                base + "/" + target);
        JsonObject json = JsonParser.parseString(response).getAsJsonObject();
        return json.get("conversion_rate").getAsDouble();
    }

    public String sendRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }
}
