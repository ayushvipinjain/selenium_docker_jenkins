FROM openjdk:8u191-jre-alpine3.8

# Installing Curl to hit the rest hub request to check if hub is ready
# Installing jq to parse the json response from the hub status request

RUN apk add curl jq

WORKDIR /tmp/automation/

ADD /target/selenium_docker_jenkins-tests.jar selenium_docker_jenkins-tests.jar
ADD /target/selenium_docker_jenkins.jar selenium_docker_jenkins.jar
ADD /target/libs libs

# Add all the external sepandancies that you need to run your tests
ADD testing.xml testing.xml
ADD crossbrowser.xml crossbrowser.xml

# Add Health Check Script
ADD healthcheck.sh healthcheck.sh

# Environment Variables
# HUB_HOST - Url of the Grid Hub
# TestNGFile - TestNg File to use to execute the tests

ENTRYPOINT sh healthcheck.sh