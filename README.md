# Data-Drivern-Framework

### Prerequisites
1. Install and set environment variable for java.
    * Windows - https://www.oracle.com/java/technologies/downloads/
    * Linux - ```  sudo apt-get install openjdk-8-jre  ```
    * MacOS - Java should already be present on Mac OS X by default.
2. Install and set environment varibale for Maven.
    * Windows - https://maven.apache.org/install.html
    * Linux/ MacOS -  [Homebrew](http://brew.sh/) (Easier)
    ```
     install maven
    ```
3. Install Docker desktop

### Run your First Test
1. Clone the Data-Drivern-Framework repository. 
```
git clone https://github.com/HardikDuva/DataDrivenFramework.git and make sure all the dependency in pom.xml file are upto date
```
2. Set-up Test Data file & Data Provider class according to requirnment
Make sure Excel sheet column field and Data provider mrthod parameter are matching

3. Write test case for Login Page(You can write test case for any page)

4. Set-up output Result Excel file name & Sheet name in checkLogin.java class

5. Remove SendResultFileToEmail method if you don't want to send result to the Email
If you want to send the result to Email 
  -Please set username password field in EmailConnector class.
  -Set required params value in sendResultFileToEmail() Method
   
6. Run docker image according to requirnment (Command -> docker compose up)

7. Run xml file from suite directory according to browser requirnment

8. After run successfully you can find you result in "TestResult" directory.