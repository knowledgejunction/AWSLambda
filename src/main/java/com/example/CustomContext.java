package com.example;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;


public final class CustomContext implements Context {
    private final String requestId;

    public String getAwsRequestId() {
        return this.requestId;
    }

    public String getLogStreamName() {
        String streamName = System.getenv("AWS_LAMBDA_LOG_STREAM_NAME");
        return streamName;
    }

    public ClientContext getClientContext() {
        return null;
    }

    public String getFunctionName() {
        return null;
    }

    public int getRemainingTimeInMillis() {
        return 0;
    }


    public LambdaLogger getLogger() {
        return null;
    }


    public String getInvokedFunctionArn() {
        return null;
    }

    public int getMemoryLimitInMB() {
        String memorySize = System.getenv("AWS_LAMBDA_FUNCTION_MEMORY_SIZE");
        if (memorySize != null) {
            return Integer.parseInt(memorySize);
        }
        return 0;
    }


    public String getLogGroupName() {
        String groupName = System.getenv("AWS_LAMBDA_LOG_GROUP_NAME");
        return groupName;
    }


    public String getFunctionVersion() {
        String functionVersion = System.getenv("AWS_LAMBDA_FUNCTION_VERSION");
        return functionVersion;
    }


    public CognitoIdentity getIdentity() {
        return null;
    }

    public CustomContext( String requestId) {
        super();
        this.requestId = requestId;
    }
}
