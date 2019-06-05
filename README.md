# DemoOrangeHRMTestNGAllure Test Automation
### Features
- BDD reporting style with TestNG and Allure.
- Page Object Model and Page Factory in Java
- Generate Screenshot each test step (available on Allure report)
- Generate Video on Failed Test (available on Allure report)
- Visual Regression Testing/Screenshot Comparison with Selenium-Shutterbug & aShot

Environment : Windows & Docker

## Download OrangeHRM
Note : This repository is using OrangeHRM version 4.0 as System Under Test
```
Windows
https://github.com/orangehrm/orangehrm
or
Mac/Linux
https://hub.docker.com/r/bitnami/orangehrm/
```


## Precondition
Install Allure :
- MacOS --> brew install allure
- Windows PC --> scoop install allure
- Linux ----> sudo apt-add-repository ppa:qameta/allure
              sudo apt-get update 
              sudo apt-get install allure
```
https://docs.qameta.io/allure/#_installing_a_commandline
https://scoop.sh/
```
![image](https://user-images.githubusercontent.com/26521948/58747484-725e6980-849e-11e9-82d7-0a6e215298ac.png)
![image](https://user-images.githubusercontent.com/26521948/58747500-9326bf00-849e-11e9-8069-fcd5eb6901a7.png)

## Run the Test Via Command Line
```
mvn clean test
```

## Create Allure Report
```
allure generate
allure open
```
or
```
allure serve
```

## Report Path
#### Sample Report
```
{your_project_path}/allure-report/index.html
```
![image](https://user-images.githubusercontent.com/26521948/58747219-b9e2f680-849a-11e9-8ae1-e5a9d5b32c0b.png)

## Demo in Youtube
```
https://youtu.be/EPd5iCTchuA
```

# DemoOrangeHRMTestNGAllure Code Quality
- Continuous Inspection with SonarQube in Docker

### Docker installation - Windows
```
https://docs.docker.com/toolbox/toolbox_install_windows/
```

### Docker installation - MacOS
```
https://docs.docker.com/docker-for-mac/install/
```
![image](https://user-images.githubusercontent.com/26521948/58690464-276c2580-83bc-11e9-9c6a-a2729cd0fba7.png)

## Demo in Youtube - Windows
```
https://youtu.be/MjgUgCN3WMg
```

# Recap
- Continuous Testing with TestNG/Allure & Continuous Inspection with SonarQube in Docker via Jenkins Continuous Integration Server

Jenkins Scripted Pipeline ---> Windows Environment
```
node(*{slave}*) {
      stage('Checkout') {git poll: true, branch: branch, credentialsId: *{credential_name}*, url: *{URL of repository}*}
  try{
      stage('Orange HRM Test Automation') {
          bat "mvn clean test"}
  } finally{
      stage('Allure Report') {
          allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'allure-results']]])}
      stage('Sonarqube Quality Gate') {
      withSonarQubeEnv('sonarqube') {
       bat "mvn sonar:sonar -Dsonar.login=*{sonar user id}* -Dsonar.password=*{sonar password}* -Dsonar.projectKey=*{project name}* -Dsonar.host.url=*{sonar URL}*"
    }}}}
```
![image](https://user-images.githubusercontent.com/26521948/58901028-df8a2d00-8732-11e9-8ce5-2a708a718227.png)

## Demo in Youtube - Windows
```
https://youtu.be/ddq8hXksH_E
```

# References :
- https://testng.org/doc/
- https://www.sonarqube.org/
- https://jenkins.io/
- http://allure.qatools.ru/
- https://github.com/pazone/ashot
- https://github.com/assertthat/selenium-shutterbug
