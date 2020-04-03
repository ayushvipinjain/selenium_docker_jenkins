#!/usr/bin/env bash
# Environment Variables
# HUB_HOST - Url of the Grid Hub
# TestNGFile - TestNg File to use to execute the tests

echo "Checking if hub is ready - $HUB_HOST"
echo "TestNGFile to be executed - $TestNGFile"

while [ "$( curl -s http://$HUB_HOST:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
  echo "Wait for Hub to get ready $HUB_HOST"
	sleep 1
done

echo "Health Check Completed"

export HUB_HOST=http://$HUB_HOST:4444/wd/hub

echo "Environment for hub $HUB_HOST"

# start the java command
java -cp selenium_docker_jenkins:selenium_docker_jenkins-tests.jar:libs/* org.testng.TestNG $TestNGFile