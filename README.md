![This is an image](https://github.com/AlexanderDankov/Chitay-Gorod-Tests/blob/master/images/Logo.png)

# Chitay-Gorod-UI-Automation Project
## UI-Automated tests for https://www.chitai-gorod.ru/

### Used tech stack:
| Java | JUnit5 | Selenide | Gradle | IntelijIDEA | Allure Report | Allure Testops | Jenkins | Selenoid | Jira | Telegram |
|------|--------|----------|--------|-------------|---------------|----------------|---------|----------|------|----------|
|![](images/JAVA.svg)|![](images/Junit5.svg)|![](images/Selenide.svg)|![](images/Gradle.svg)|![](images/IDEA.svg)|![](images/AllureReport.svg)|![](images/AllureTestops.svg)|![](images/Jenkins.svg)|![](images/Selenoid.svg)|<img src="images/Jira.svg" width=100 height=100>|![](images/Telegram.svg)|

### Launch Parameters:
- BROWSER (default Chrome)
- VERSION (default 100.0)
- SIZE (default 1024*768)
- THREADS (default 1)

![](images/JenkinsParameters.png)

### To run tests localy use this command:
```
gradle clean test -Dbrowser=${your_browser} -Dversion=${your_version} -Dsize=${your_size} -Dthreads=${your_number_of_threads}
```
### Or run tests, using default settings:
```
gradle clean test
```
## <img src="images/Jenkins.svg" width=20 height=20> Jenkins:
### Job overview:
![](images/JenkinsOverview.png)

## <img src="images/AllureReport.svg" width=20 height=20> Allure Report:
### Overview:
![](images/AllureOverview.png)

### Tests with steps, attached screenshots, page sources, logs and video:
![](images/AllureSuites.png)

### Passed test video:
![](images/Video.gif)

## <img src="images/AllureTestops.svg" width=20 height=20> Allure Testops:
### Launch:
We can see details for each launch of tests in real-time in TMS Allure Testops

![](images/AllureTestopsOverview.png)

### Test cases:
![](images/AllureTestopsCases.png)

## <img src="images/Jira.svg" width=20 height=20> Jira integration:
Test cases and test runs can be integrated to specific issue in Jira

![](images/JiraOverview.png)

## <img src="images/Telegram.svg" width=20 height=20> Telegram notifications:
After every test run we can get notification with short report

![](images/TelegramNotification.png)

