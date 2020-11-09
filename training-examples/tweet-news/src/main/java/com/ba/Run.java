package com.ba;

import com.ba.dto.ResponseDTO;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Run {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpResponse<String> response = sendHTTPRequest();

        // print response headers
        printResponseHeader(response);

        // print status code
        System.out.println(response.statusCode());

        ResponseDTO responseDTO = convertResponseDTO(response);

        System.out.println(responseDTO);
    }

    private static ResponseDTO convertResponseDTO(HttpResponse<String> response) {
        // print response body
        System.out.println(response.body());

        Gson gson = new Gson();
        ResponseDTO responseDTO = gson.fromJson(response.body(), ResponseDTO.class);
        return responseDTO;
    }

    private static void printResponseHeader(HttpResponse<String> response) {
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
    }

    private static HttpResponse<String> sendHTTPRequest() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(prepareURL()))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static String prepareURL() {
        StringBuilder urlBuilder = new StringBuilder("http://newsapi.org/v2/top-headlines");
        urlBuilder.append("?country=us");
        urlBuilder.append("&apiKey=71e49b567786452da0ece5c60cbd5318");

        return urlBuilder.toString();
    }
}
