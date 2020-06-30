package com.github.md;

import java.util.Collections;
import java.util.Map;

public class ApiGatewayRequest {

   private String path;

   private String httpMethod;

   private Map<String,Object> headers= Collections.emptyMap();

   private Map<String,Object>  queryStringParameters=Collections.emptyMap();

   private Map<String,Object>  pathParameters=Collections.emptyMap();

   private String body = "";

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
}
