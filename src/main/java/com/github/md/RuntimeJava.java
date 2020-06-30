package com.github.md;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import kotlin.Metadata;
import kotlin.TypeCastException;
import org.jetbrains.annotations.NotNull;

public final class RuntimeJava {
   @NotNull
   public static final String handlerVariableName = "_HANDLER";

   public static final void main() {
      String handler1 = System.getenv("_HANDLER");
      Object instance = Class.forName(handler1).newInstance();
      if (instance != null) {
         RequestHandler handler = (RequestHandler)instance;
         Runtime runtime = new Runtime((new AwsRuntimeApi()), handler);

         while(true) {
            runtime.processInvocation();
         }
      }

      throw new TypeCastException("null cannot be cast to non-null type com.amazonaws.services.lambda.runtime.RequestHandler<com.github.md.ApiGatewayRequest, com.github.md.ApiGatewayResponse>");
   }

   public static void main(String[] var0) {
      main();
   }
}
