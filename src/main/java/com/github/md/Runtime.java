package com.github.md;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.jetbrains.annotations.NotNull;

public final class Runtime {
   private final RuntimeApi runtimeApi;
   private final RequestHandler handler;

   public final void processInvocation() {
      ApiGatewayRequest apiGatewayRequest = this.runtimeApi.getInvocation();
      ApiGatewayResponse apiGatewayResponse = (ApiGatewayResponse)this.handler.handleRequest(apiGatewayRequest, new CustomContext(this.runtimeApi.getCurrentRequestId()));
      RuntimeApi runtimeApi = this.runtimeApi;
      runtimeApi.sendResponse(apiGatewayResponse);
   }

   public Runtime(@NotNull RuntimeApi runtimeApi, @NotNull RequestHandler handler) {
      super();
      this.runtimeApi = runtimeApi;
      this.handler = handler;
   }
}
