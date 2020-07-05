package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RuntimeApi {
   String getCurrentRequestId();
   ApiGatewayRequest getInvocation();
   void sendResponse(ApiGatewayResponse var1);
}