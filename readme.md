
## Selenium - Docker - Jenkins

#### Running Tests Locally

    exectype=local in config.properties
    
    Command Line
    `mvn clean test -DdefaultSuiteXMLFile=crossbrowser.xml`
    `mvn clean test -DdefaultSuiteXMLFile=testing.xml`

    Intellij
    Set the testng file in pom.xml
    Set Test Parameters 
    browser = chrome or firefox
    
#### Running Test on Grid on Local
     exectype=grid in config.properties
     
    `Set Env Variable HUB_HOST= {url for Hub}
     example : HUB_HOST=http://127.0.0.1:4446/wd/hub`
    
#### Create Maven Package 
   `mvn clean package -DskipTests`
   
Note -> -DskipTests to skip running the tests when creating the package
Once the package is created to test if you are able to run your tests run below command from terminal

Step 1 -> Go to target directory

          `cd target`
Step 2 -> Run Tests

      For MAC/Linux - `java -cp selenium_docker_jenkins:selenium_docker_jenkins-tests.jar:libs/* org.testng.TestNG ../testing.xml`
      
      For Windows - `java -cp selenium_docker_jenkins;selenium_docker_jenkins-tests.jar;libs/* org.testng.TestNG ../testing.xml`

Note - when you run the above command the suite present in testing.xml should be executed 

## Running Test using Docker 

#### Build Image from Dockerfile
  `docker build -t=ayushvipinjain/selenium_docker_jenkins .`

:point_right: . indicates Dockerfile is present in current directory

#### Verify the images created by running the followimg command and you will see the images in the list
    `docker images`
    
####  Mounting test outputs to local machine to view reports 
``docker run --entrypoint=/bin/sh -v /Users/in-jayush/Vapasi:/tmp/automation/test-output -it ayushvipinjain/selenium_docker_jenkins``

Run below command for testing to see the reports are coming on local directory
example -
 
 `java -cp selenium_docker_jenkins:selenium_docker_jenkins-tests.jar:libs/* org.testng.TestNG testing.xml`

The files in test-output folder in container should be present on local at the folder used for volume mapping.
In the above example it is 
`/Users/in-jayush/Vapasi`

#### Command to Execute test via Dockerfile

``docker run -v /Users/in-jayush/Vapasi:/tmp/automation/test-output -e HUB_HOST="http://192.168.0.104:4446/wd/hub" -e TestNGFile="testing.xml" -it  ayushvipinjain/selenium_docker_jenkins``   

:point_right: If the selenium grid is on local use machine ip as the HUB_HOST

On Mac to find ip use below command

`ifconfig | grep "inet " | grep -v 127.0.0.1`

Your IP address will be displayed next to the “inet” entry.

# Create Grid Infrastructure using docker compose
* Manually 


    * Registed Hub - 

     `docker run -p 4446:4444 --name seleniumhub -d selenium/hub` 

    * Register Chrome Node -

    `docker run --name chomebrowser -p 5901:5900 --link seleniumhub:hub -P -d selenium/node-chrome-debug`

    * Register Firefox Node -

    `docker run --name firefoxbrowser -p 5902:5900 --link seleniumhub:hub -P -d selenium/node-firefox-debug`

    * Multiple Browser Instances Example

    `docker run --name chomebrowser -p 5903:5900 --link seleniumhub:hub -P -e NODE_MAX_INSTANCES=5 -e NODE_MAX_SESSION=5 -d selenium/node-chrome-debug`

* Automated - Refer docker-compose.yml

To Run `docker-compose up`
To Stop `docker-compose down`

Visit  `http://localhost:4466//wd/hub` for Hub

###### <--------------------------------------- END - Covered in Branch Level1---------------------------------------------------------------->

* Dockerfile is running the tests and docker-compose is creating the grid Infrastructure
* Running the tests can be integrated in docker-compose file
* Introducing automation-service that will use the image created using the Dockerfile and Run it.


Issues Faced -  

automation_service :

        image: ayushvipinjain/selenium_docker_jenkins
        
        links:
        
          - chromenode

          - firefoxnode
          

firefoxnode :

        links:
        
            - seleniumhub:hub
 
 chromenode :
 
        links:
        
            - seleniumhub:hub


automation_service ---dependsOn--> Nodes ---dependsOn--> Hub


* First the hub is created
* Chrome and Firefox node gets created
* Immediately after browser node gets created automation_service is executed and send the automation execution request to hub.
* By this time Although the Node and Hub is created but the Node is not registered with Hub. It takes few secs to connect and hence the the hub receives the request it fails the tests.
Error : Empty pool of VM for setup Capabilities
##### Problem Statement -> Need to ensure that before the request from automation_service goes to hub, hub should be ready and connected to the nodes.

###### <------------------------------------------ END - Covered in Branch Level2------------------------------------------------------------>

* Solution - The hub api gives the status when hub is ready and connected to any nodes or not
 
 `http://$HUB_HOST:4444/wd/hub/status`
* Parse the response and read the ready attribute
Prerequisite - Install curl for hit the hub api and jq for response parsing on container

* HealthCheck - Shell script to ensure that hub is ready and execute the tests


###### <-------------------------------------------- END - Covered in Branch Level3--------------------------------------------------------->


#### Branches
* ###### _[Level1] - Execute tests via Dockerfile and Setup Grid Infrastructure using docker-compose
* ###### _[Level2] - Integration between Dockerfile and docker-compose.yml and challenges related to Empty pool of VM for setup Capabilities
* ###### _[Level3] - HealthCheck - Shell script to ensure that hub is ready and execute the tests




#### Resources
:point_right: Docker Docs - `https://docs.docker.com/`

:point_right: docker-compose for selenium `https://github.com/SeleniumHQ/docker-selenium`

:point_right: https://www.vinsguru.com/selenium-grid-setup-using-docker/
