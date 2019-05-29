# OrangeHRMTestNGAllure

Orange HRM - Allure - TestNG

## Precondition
Windows --> Install scoop to install Allure
```
https://docs.qameta.io/allure/#_installing_a_commandline
https://scoop.sh/
```

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
