
## Selenium - Docker - Jenkins

#### Running Tests Locally

    Command Line
    `mvn clean test -DdefaultSuiteXMLFile=crossbrowser.xml`
    `mvn clean test -DdefaultSuiteXMLFile=testing.xml`

    Intellij
    Set the testng file in pom.xml
    Set Test Parameters 
    browser = chrome or firefox
    
#### Running Test on Grid
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
docker run --entrypoint=/bin/sh -v /Users/in-jayush/Vapasi:/tmp/automation/test-output -it ayushvipinjain/selenium_docker_jenkins

Run below command for testing to see the reports are coming on local directory
example - `java -cp selenium_docker_jenkins:selenium_docker_jenkins-tests.jar:libs/* org.testng.TestNG testing.xml`

The files in test-output folder in container should be present on local at the folder used for volume mapping.
In the above example it is `/Users/in-jayush/Vapasi`

#### Command to Execute test via Dockerfile

``docker run -v /Users/in-jayush/Vapasi:/tmp/automation/test-output -e HUB_HOST="http://192.168.0.104:4446/wd/hub" -e TestNGFile="testing.xml" -it  ayushvipinjain/selenium_docker_jenkins``   

:point_right: If the selenium grid is on local use machine ip as the HUB_HOST

# Create Grid Infrastructure using docker compose

 Refer docker-compose.yml

To Run `docker-compose up`
To Stop `docker-compose down`

Visit  `http://localhost:4466//wd/hub` for Hub

