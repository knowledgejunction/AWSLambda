package com.example;

import java.util.HashMap;
import java.util.Map;

public class ApiGatewayRequest {

   private String path;

   private String httpMethod;

   private Map<String,Object> headers;

   private Map<String,Object>  queryStringParameters;

   private Map<String,Object>  pathParameters;

   private String body;

   public String getPath() {
      return path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   public String getHttpMethod() {
      return httpMethod;
   }

   public void setHttpMethod(String httpMethod) {
      this.httpMethod = httpMethod;
   }

   public Map<String, Object> getHeaders() {
      return headers;
   }

   public void setHeaders(Map<String, Object> headers) {
      this.headers = headers;
   }

   public Map<String, Object> getQueryStringParameters() {
      return queryStringParameters;
   }

   public void setQueryStringParameters(Map<String, Object> queryStringParameters) {
      this.queryStringParameters = queryStringParameters;
   }

   public Map<String, Object> getPathParameters() {
      return pathParameters;
   }

   public void setPathParameters(Map<String, Object> pathParameters) {
      this.pathParameters = pathParameters;
   }

   public String getBody() {
      return body;
   }

   public void setBody(String body) {
      this.body = body;
   }

   public ApiGatewayRequest() {
      pathParameters = new HashMap<>();
      headers = new HashMap<>();
      queryStringParameters = new HashMap<>();
   }

   public ApiGatewayRequest(String path, String httpMethod, Map<String, Object> headers, Map<String, Object> queryStringParameters, Map<String, Object> pathParameters, String body) {
      this.path = path;
      this.httpMethod = httpMethod;
      this.headers = headers;
      this.queryStringParameters = queryStringParameters;
      this.pathParameters = pathParameters;
      this.body = body;
   }
}
