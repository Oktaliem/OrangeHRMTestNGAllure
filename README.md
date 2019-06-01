# DemoOrangeHRMTestNGAllure Test Automation
### Features
- BDD reporting style with TestNG and Allure.
- Generate Screenshot after run the test (available on Allure report)
- Generate Video on Failed Test (available on *{project_folder}*/video/..)
- Page Object Model and Page Factory in Java
- Visual Regression Testing with Selenium-Shutterbug & aShot


## Download OrangeHRM
Note : This repository is using OrangeHRM version 4.0 as System Under Test
```
https://github.com/orangehrm/orangehrm
or
https://hub.docker.com/r/bitnami/orangehrm/
```


## Precondition
Install Allure :
- MacOS --> brew install allure
- Windows PC --> scoop install allure
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
Clone this repository and open :
```
{your_project_path}/allure-report/index.html
```
```
{your_project_path}/video/...
```
![image](https://user-images.githubusercontent.com/26521948/58747219-b9e2f680-849a-11e9-8ae1-e5a9d5b32c0b.png)

## Demo in Youtube
```
https://youtu.be/EPd5iCTchuA
```

# DemoOrangeHRMTestNGAllure Continuous Inspection
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

# DemoOrangeHRMTestNGAllure Continuous Testing
- Continuous Testing (TestNG & Allure) & Continuous Inspection (SonarQube) with Continous Integration Server (Jenkins Pipeline) in Docker

To be continued......
