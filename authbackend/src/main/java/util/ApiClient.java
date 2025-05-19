package com.example.authclient.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiClient {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String post(String endpoint, Map<String, String> data) throws Exception {
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = objectMapper.writeValueAsBytes(data);
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() == 200) {
            return new String(conn.getInputStream().readAllBytes());
        } else {
            throw new RuntimeException("Failed with HTTP code: " + conn.getResponseCode());
        }
    }
}
