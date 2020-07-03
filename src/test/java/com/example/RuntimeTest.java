package com.example;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RuntimeTest {
   public static void run() {
      String dynamoPort = System.getenv("DYNAMO_PORT");
      if (dynamoPort == null) {
         dynamoPort = "8000";
      }

      String dynamoHost = System.getenv("DYNAMO_HOST");
      if (dynamoHost == null) {
         dynamoHost = "localhost";
      }
      try {
      DynamoDbClient dbClient = DynamoDbClient.builder().region(Region.US_WEST_2)
              .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("dummy", "dummy")))
              .endpointOverride(new URI("http://" + dynamoHost + ':' + dynamoPort)).build();
      new DynamoDBSetup(dbClient).createTables();

      Class handlerClass = Class.forName("com.example.Handler");
      handlerClass.newInstance();
         ApiGatewayRequest request = new ApiGatewayRequest();
         Map<String, Object> pathParams = new HashMap<>();
         pathParams.put("name","Suraj Test");
         request.setPathParameters(pathParams);
         RuntimeApi  runtimeApi = new MockRuntime(request);
         Handler handler = new Handler(dbClient);
         new Runtime(runtimeApi,handler).processInvocation();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
   public static void main(String[] args) {
      run();
   }
}
