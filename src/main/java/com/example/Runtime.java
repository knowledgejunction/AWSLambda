package com.example;

import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Runtime {
   private final RuntimeApi runtimeApi;
   private final RequestHandler handler;

   public final void processInvocation() {
      ApiGatewayRequest apiGatewayRequest = this.runtimeApi.getInvocation();
      ApiGatewayResponse apiGatewayResponse = (ApiGatewayResponse)this.handler.handleRequest(apiGatewayRequest, new CustomContext(this.runtimeApi.getCurrentRequestId()));
      RuntimeApi runtimeApi = this.runtimeApi;
      runtimeApi.sendResponse(apiGatewayResponse);
   }

   public Runtime(RuntimeApi runtimeApi,RequestHandler handler) {
      super();
      this.runtimeApi = runtimeApi;
      this.handler = handler;
   }
}
