package com.github.md;

public interface RuntimeApi {
   String getCurrentRequestId();
   ApiGatewayRequest getInvocation();
   void sendResponse(ApiGatewayResponse var1);
}