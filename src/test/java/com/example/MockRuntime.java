package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

public  class MockRuntime implements RuntimeApi {
   private final ObjectMapper objectMapper;
   private final String currentRequestId;
   private final ApiGatewayRequest request;

   public String getCurrentRequestId() {
      return this.currentRequestId;
   }

   public ApiGatewayRequest getInvocation() {
      try {
         objectMapper.writeValueAsString(request);
      } catch (JsonProcessingException e) {
         e.printStackTrace();
      }
      return request;
   }

   public void sendResponse(ApiGatewayResponse response) {
      String var2 = null;
      try {
         var2 = "received response " + this.objectMapper.writeValueAsString(response);
      } catch (JsonProcessingException e) {
         e.printStackTrace();
      }
      System.out.println(var2);
   }

   public final ApiGatewayRequest getRequest() {
      return this.request;
   }

   public MockRuntime(ApiGatewayRequest request) {
      super();
      this.request = request;
      this.objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
      this.currentRequestId = UUID.randomUUID().toString();
      try {
         String jsonValue = objectMapper.writeValueAsString(request);
         ApiGatewayRequest temp = objectMapper.readValue(jsonValue,ApiGatewayRequest.class);
      } catch (JsonProcessingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
