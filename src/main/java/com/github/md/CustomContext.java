package com.github.md;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.jetbrains.annotations.NotNull;

public final class CustomContext implements Context {
    private final String requestId;

    @NotNull
    public String getAwsRequestId() {
        return this.requestId;
    }

    @NotNull
    public String getLogStreamName() {
        String streamName = System.getenv("AWS_LAMBDA_LOG_STREAM_NAME");
        return streamName;
    }

    @NotNull
    public ClientContext getClientContext() {
        return null;
    }

    @NotNull
    public String getFunctionName() {
        return null;
    }

    public int getRemainingTimeInMillis() {
        return 0;
    }

    @NotNull
    public LambdaLogger getLogger() {
        return null;
    }

    @NotNull
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

    @NotNull
    public String getLogGroupName() {
        String groupName = System.getenv("AWS_LAMBDA_LOG_GROUP_NAME");
        return groupName;
    }

    @NotNull
    public String getFunctionVersion() {
        String functionVersion = System.getenv("AWS_LAMBDA_FUNCTION_VERSION");
        return functionVersion;
    }

    @NotNull
    public CognitoIdentity getIdentity() {
        return null;
    }

    public CustomContext(@NotNull String requestId) {
        super();
        this.requestId = requestId;
    }
}
