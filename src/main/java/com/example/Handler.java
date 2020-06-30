package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Handler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {
    private DynamoDbClient dynamoClient;

    public Handler(DynamoDbClient dynamoClient) {
        super();
        this.dynamoClient = dynamoClient;
    }

    public Handler() {
        this(DynamoDbClient.create());
    }

    public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context) {
        String name = "";
        Map params = input.getPathParameters();
        if (params != null) {
            name = String.valueOf(params.get("name"));
        }

        Map<String, AttributeValue> items = new HashMap<>();
        items.put("id", AttributeValue.builder()
                .s(UUID.randomUUID().toString())
                .build());
        items.put("name", AttributeValue.builder()
                .s(name)
                .build());

        this.dynamoClient.putItem(PutItemRequest.builder().tableName("greetings")
                .item(items).build());
        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Powered-By", "AWS Lambda and Serverless");
        ApiGatewayResponse apiGatewayResponse = new ApiGatewayResponse();
        apiGatewayResponse.setStatusCode(200);
        apiGatewayResponse.setBody("Hello :" + name);
        apiGatewayResponse.setHeaders(headers);
        return null;
    }
}
