package com.example;

public interface RuntimeApi {
   String getCurrentRequestId();
   ApiGatewayRequest getInvocation();
   void sendResponse(ApiGatewayResponse var1);
}