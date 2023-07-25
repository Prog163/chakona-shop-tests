# Chakona-UI-Automation Project
## UI-Automated tests for https://chaconne.ru/

### Used tech stack:
| Java | JUnit5 | Selenide | Gradle | Intelij IDEA | Allure Report | Allure Testops | Jenkins | Selenoid | Jira | Telegram |
|------|--------|----------|--------|--------------|---------------|----------------|---------|----------|------|----------|
|![](images/JAVA.svg)|![](images/Junit5.svg)|![](images/Selenide.svg)|![](images/Gradle.svg)|![](images/IDEA.svg)|![](images/AllureReport.svg)|![](images/AllureTestops.svg)|![](images/Jenkins.svg)|![](images/Selenoid.svg)|<img src="images/Jira.svg" width=100 height=100>|![](images/Telegram.svg)|

### Launch Parameters:
- TASK (default test)
- BROWSER (default Chrome)
- VERSION (default 100.0)
- SIZE (default 1024*768)
- THREADS (default 1)
- REMOTE_URL (deafult selenoid.autotests.cloud)


![](images/Jankins1.png)

### To run tests localy use this command:
```
gradle clean ${your_task} -Dbrowser=${your_browser} -Dversion=${your_version} -Dsize=${your_size} -Dthreads=${your_number_of_threads}
```
### Or run tests, using default settings:
```
gradle clean test
```
## <img src="images/Jenkins.svg" width=20 height=20> Jenkins:
### Job overview:
![](images/JobOverview.png)

## <img src="images/AllureReport.svg" width=20 height=20> Allure Report:
### Overview:
![](images/AllurePage.png)

### Tests with steps, attached screenshots, page sources, logs and video:
![](images/AllureStepsResult.png)

### Passed test video:
![](GIF!)

## <img src="images/AllureTestops.svg" width=20 height=20> Allure Testops:
### Launch:
We can see details for each launch of tests in real-time in TMS Allure Testops

![](IMAGE!)

Dashboard

![](images/Dashboardtestops.png)

### Test cases:
![](images/TKfromtestops.png)

## <img src="images/Jira.svg" width=20 height=20> Jira integration:
Test cases and test runs can be integrated to specific issue in Jira

![](images/Jiraintegration.png)

## <img src="images/Telegram.svg" width=20 height=20> Telegram notifications:
After every test run we can get notification with short report

![](IMAGE!)

