##Selenium - Docker - Jenkins

####Create Maven Package 
   `mvn clean package -DskipTests`
   
Note - -DskipTests to skip running the tests when creating the package

Once the package is created to test if you are able to run your tests run below command from terminal

Step 1 -> Go to target directory
          `cd target`
Step 2 -> Run Tests
      For MAC/Linux - `java -cp selenium_docker_jenkins:selenium_docker_jenkins-tests.jar:libs/* org.testng.TestNG ../testing.xml`
      For Windows - `java -cp selenium_docker_jenkins;selenium_docker_jenkins-tests.jar;libs/* org.testng.TestNG ../testing.xml`

Note - when you run the above command the suite present in testing.xml should be executed 

