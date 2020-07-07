# AWSLambda
This project is inspired by https://medium.com/@mathiasdpunkt/ease-creation-of-graalvm-native-images-using-assisted-configuration-68a86dea07c7.
This is refer by https://github.com/mduesterhoeft/kotlin-graalvm-custom-aws-lambda-runtime-talk/tree/dynamodb-agent

To build and deploy the function run the following:

```
# run the test runtime and use the native image agent to infer native image build configuration
./test-runtime-agent.sh
# build the GraalVM native image and package the runime
./package.sh 
