package com.example;

import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CustomRuntime {
   public static final String handlerVariableName = "_HANDLER";

   public static void run() {
      try {
      String handler = System.getenv(handlerVariableName);
      Object instance = null;

         instance = Class.forName(handler).newInstance();
         if (instance != null) {
            RequestHandler requestHandler = (RequestHandler) instance;
            Runtime runtime = new Runtime((new AwsRuntimeApi()), requestHandler);

            while (true) {
               runtime.processInvocation();
            }
         }
      } catch (InstantiationException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }



   }

   public static void main(String[] var0) {
     run();
   }
}
