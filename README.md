# DemoOrangeHRMTestNGAllure
- BDD sytle reporting with TestNG and Allure.
- Alternative (Behavior-Driven Development) BDD Test Automation Reporting without Cucumber Framework
- Generate Sreenshot after run the test

## Download OrangeHRM
```
https://github.com/orangehrm/orangehrm
```
or
```
https://hub.docker.com/r/bitnami/orangehrm/
```


## Precondition
- MacOS --> brew install allure
- Windows PC --> Install scoop to install Allure
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
Clone this repository to your PC and go to :

Allure
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
