package dev.anthony.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.anthony.model.Conversion;
import dev.anthony.model.Currency;
import dev.anthony.api.ExchangeRateClient;
import dev.anthony.util.FileHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class ConverterService {
    private final ExchangeRateClient client = new ExchangeRateClient();
    private final Map<String, String> currencies = new TreeMap<>();
    private static final String HISTORY_FILE = "conversiong.log";

    public void init() {
        try {
            String json = client.fetchSupportedCodes();
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
            obj.getAsJsonArray("supported_codes").forEach(element -> {
                var pair = element.getAsJsonArray();
                currencies.put(pair.get(0).getAsString(), pair.get(1).getAsString());
            });
        } catch (Exception e) {
            System.out.println("Error initializing currencies: " + e.getMessage());
        }
    }

    public Map<String, String> getAvailableCurrencies() {
        return currencies;
    }

    public Conversion performConversion(String fromCode, String toCode, double amount) throws IOException, InterruptedException {
        double rate = client.fetchRate(fromCode, toCode);
        Currency from = new Currency(fromCode, currencies.get(fromCode));
        Currency to = new Currency(toCode, currencies.get(toCode));

        Conversion result = new Conversion(from, to, amount, amount * rate, LocalDateTime.now());
        FileHandler.saveLine(HISTORY_FILE, result.toString());
        return result;
    }

    public String getHistory() {
        return FileHandler.readContent(HISTORY_FILE);
    }
}
