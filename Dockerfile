FROM openjdk:8u191-jre-alpine3.8

WORKDIR /tmp/automation/

ADD /target/selenium_docker_jenkins-tests.jar selenium_docker_jenkins-tests.jar
ADD /target/selenium_docker_jenkins.jar selenium_docker_jenkins.jar
ADD /target/libs libs

# Add all the external sepandancies that you need to run your tests
ADD testing.xml testing.xml
ADD crossbrowser.xml crossbrowser.xml
# HUB_HOST - Url of the Grid Hub
# TestNGFile - TestNg File to use to execute the tests

ENTRYPOINT java -cp selenium_docker_jenkins:selenium_docker_jenkins-tests.jar:libs/* -DHUB_HOST=$HUB_HOST org.testng.TestNG $TestNGFile