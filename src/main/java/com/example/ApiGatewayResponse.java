package com.example;


import java.util.Map;

public class ApiGatewayResponse {
   private  int statusCode;
   private String body;
   private  Map<String,Object> headers;
   private  boolean isBase64Encoded;

   public int getStatusCode() {
      return statusCode;
   }

   public void setStatusCode(int statusCode) {
      this.statusCode = statusCode;
   }

   public String getBody() {
      return body;
   }

   public void setBody(String body) {
      this.body = body;
   }

   public Map<String, Object> getHeaders() {
      return headers;
   }

   public void setHeaders(Map<String, Object> headers) {
      this.headers = headers;
   }

   public boolean isBase64Encoded() {
      return isBase64Encoded;
   }

   public void setBase64Encoded(boolean base64Encoded) {
      isBase64Encoded = base64Encoded;
   }

   public ApiGatewayResponse() {
   }

   public ApiGatewayResponse(int statusCode, String body, Map<String, Object> headers, boolean isBase64Encoded) {
      this.statusCode = statusCode;
      this.body = body;
      this.headers = headers;
      this.isBase64Encoded = isBase64Encoded;
   }
}
