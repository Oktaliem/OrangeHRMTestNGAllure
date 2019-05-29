# OrangeHRMTestNGAllure

Orange HRM - Allure - TestNG

## Precondition
Windows --> Install scoop to install Allure
```
https://docs.qameta.io/allure/#_installing_a_commandline
https://scoop.sh/
```
![install Allure](https://user-images.githubusercontent.com/26521948/58542313-f52dbd00-822f-11e9-9ba4-abd290b1a9de.png)

## Run the Test Via Command Line
```
mvn clean test
```

## Create Allure Report
```
allure generate
```
or
```
allure serve
```

## Report Path
Allure
```
{your_project_path}/allure-report/index.html
```
Extent Reports
```
{your_project_path}/ExtentReports/ExtentReportResults.html
```
