package com.herron.exchange.common.api.common.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestHandler.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T postRequest(String url, String body, Class<T> responseClass) {
        return postRequest(url, body, Map.of(), responseClass);
    }

    public static <T> T postRequest(String url, String body, Map<String, String> headers, Class<T> responseClass) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Post request for {} with body {} received.", url, body);
        }
        var request = HttpRequest.newBuilder();
        request.uri(URI.create(url))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(body));
        headers.forEach(request::headers);

        return request(request.build(), responseClass);
    }

    public static <T> T getRequest(String url, Class<T> responseClass) {
        return getRequest(url, Map.of(), responseClass);
    }

    public static <T> T getRequest(String url, Map<String, String> headers, Class<T> responseClass) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get request for {} received.", url);
        }
        var request = HttpRequest.newBuilder();
        request.uri(URI.create(url))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody());
        headers.forEach(request::headers);

        return request(request.build(), responseClass);
    }

    private static <T> T request(HttpRequest request, Class<T> responseClass) {
        try {
            var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Response received for {} with status {}.", response.uri(), response.statusCode());
            }
            return mapResponse(response.body(), responseClass);
        } catch (InterruptedException | IOException e) {
            LOGGER.error("Request failed", e);
            return null;
        }
    }

    public static <T> T mapResponse(String response, Class<T> responseClass) {
        try {
            response = handleSpecialCharacters(response);
            return MAPPER.readValue(response, responseClass);
        } catch (IOException e) {
            LOGGER.error("Error while mapping response.", e);
            return null;
        }
    }

    private static String handleSpecialCharacters(String response) {
        return response.replace("\\", "\\\\");
    }

    public static <T> String convertToQuery(T queryParamClass) {
        try {
            String jsonRequest = MAPPER.writeValueAsString(queryParamClass);
            Map<String, String> queryParams = MAPPER.readValue(jsonRequest, Map.class);
            return convertToQuery(queryParams);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while mapping response", e);
            return null;
        }
    }

    public static String convertToQuery(Map<String, String> queryParams) {
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (!query.isEmpty()) {
                query.append("&");
            } else {
                query.append("?");
            }

            query.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            query.append("=");
            query.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));

        }

        return query.toString();
    }

    public static HttpResponse<String> getRawRequest(String url, String bearer) {
        LOGGER.info("Get request for {} received.", url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + bearer)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return rawRequest(request);
    }

    private static HttpResponse<String> rawRequest(HttpRequest request) {
        try {
            var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            LOGGER.info("Response received for {} with status {}.", response.uri(), response.statusCode());
            return response;
        } catch (InterruptedException | IOException e) {
            LOGGER.error("Request failed", e);
            return null;
        }
    }
}