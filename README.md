# DemoOrangeHRMTestNGAllure Test Automation
- BDD reporting style with TestNG and Allure.
- Generate Screenshot after run the test (available on Allure report)
- Page Object Model and Page Factory in Java

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
![image](https://user-images.githubusercontent.com/26521948/58542559-68cfca00-8230-11e9-9885-755963981310.png)
![install Allure](https://user-images.githubusercontent.com/26521948/58542313-f52dbd00-822f-11e9-9ba4-abd290b1a9de.png)

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
![allure overview](https://user-images.githubusercontent.com/26521948/58542704-a5032a80-8230-11e9-9321-9ddfc7d5fd07.png)
![allure suites](https://user-images.githubusercontent.com/26521948/58542714-a7fe1b00-8230-11e9-9e78-30943787d403.png)
![allure categories](https://user-images.githubusercontent.com/26521948/58542721-aa607500-8230-11e9-8fb5-9c282a92903f.png)
![allure graphs](https://user-images.githubusercontent.com/26521948/58542728-ad5b6580-8230-11e9-9bc7-4a88ebdaeda1.png)

## Demo in Youtube
```
https://youtu.be/EPd5iCTchuA
```

# DemoOrangeHRMTestNGAllure Continuous Inspection
- Continuous inspection with SonarQube in Docker

### Docker installation - Windows
```
https://docs.docker.com/toolbox/toolbox_install_windows/
```

### Docker installation - MacOS
```
https://docs.docker.com/docker-for-mac/install/
```

## Demo in Youtube - Windows
```
https://youtu.be/MjgUgCN3WMg
```

# DemoOrangeHRMTestNGAllure Continuous Testing
- Continuous Testing with Jenkins in Docker

To be continued......
