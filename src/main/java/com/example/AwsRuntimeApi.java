package com.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class AwsRuntimeApi implements RuntimeApi {
    private String currentRequestId = "";
    private  HttpURLConnection connection;
    private  ObjectMapper objectMapper;
    private  String runtimeApiEndpoint;
    private static final String requestIdHeaderName = "lambda-runtime-aws-request-id";
    private static final String runtimeApiEndpointVariableName = "AWS_LAMBDA_RUNTIME_API";

    private static final String DEFAULT_MEDIA_TYPE = "application/json";
    private static final int DEFAULT_TIMEOUT_MILLIS = 5000;

    public AwsRuntimeApi() {
        runtimeApiEndpoint = System.getenv("AWS_LAMBDA_RUNTIME_API");
        objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public String getCurrentRequestId() {
        return this.currentRequestId;
    }

    public void setCurrentRequestId( String currentRequestId) {
        this.currentRequestId = currentRequestId;
    }

    @Override
    public ApiGatewayRequest getInvocation() {
        final String url = "http://" + runtimeApiEndpoint + "/2018-06-01/runtime/invocation/next";
        try {
            final HttpURLConnection connection = createConnectionObject(url);
            connection.setRequestMethod("GET");

            validateResponse(connection);
            final String responseBody = readResponseBody(connection);
            this.setCurrentRequestId(connection.getHeaderField("lambda-runtime-aws-request-id"));
            System.out.println("****** Request *****"+responseBody);
            ApiGatewayRequest  request = objectMapper.readValue(responseBody,ApiGatewayRequest.class);
            return request;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendResponse(ApiGatewayResponse apiGatewayResponse) {
        final String url =
                "http://" + runtimeApiEndpoint + "/2018-06-01/runtime/invocation/" + this.getCurrentRequestId() + "/response";
        try {
            postWithoutResponse(objectMapper.writeValueAsString(apiGatewayResponse), url);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private HttpURLConnection createConnectionObject(String path) throws IOException {
        final URL url = new URL(path);
        final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(DEFAULT_TIMEOUT_MILLIS);
        return httpURLConnection;
    }

    private void validateResponse(HttpURLConnection connection) throws Exception {
        if (connection.getResponseCode() >= 400) {
            final String responseBody = readResponseBody(connection);
            throw new Exception(connection.getResponseCode() + " response from url: " + connection.getURL() + " body: " + responseBody);
        }
    }

    private String readResponseBody(HttpURLConnection connection) throws IOException {
        InputStream inputStream;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            inputStream = connection.getErrorStream();
        }
        if (inputStream == null) {
            return "";
        }
        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            final StringBuilder stringBuilder = new StringBuilder(calculateBufferSize(connection));
            int oneChar;
            while ((oneChar = bufferedReader.read()) != -1) {
                stringBuilder.append((char) oneChar);
            }
            return stringBuilder.toString();
        }
    }

    private int calculateBufferSize(HttpURLConnection connection) {
        final int contentLength = connection.getContentLength();
        return contentLength < 0 ? 256 : contentLength;
    }

    private void postWithoutResponse(String requestBody, String url) throws Exception {
        try {
            final HttpURLConnection connection = createConnectionObject(url);
            connection.setRequestMethod("POST");
            connection.addRequestProperty("content-type", DEFAULT_MEDIA_TYPE);
            connection.setDoOutput(true);

            writeRequestBody(requestBody, connection);
            validateResponse(connection);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    private void writeRequestBody(String requestBody, HttpURLConnection connection) throws IOException {
        try (final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream())) {
            outputStreamWriter.write(requestBody);
        }
    }
}
