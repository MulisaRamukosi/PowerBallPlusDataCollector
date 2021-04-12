# PowerBallPlusDataCollector
Collects powerball plus historical results by using a robot that automates the nationallottery.co.za and scraps the data and save to a csv file
### SETUP
### Requirements
- Java 8
- latest Intellij IDEA
- Google Chrome
### How to run the project
To run the project, a google chrome driver is required, it can be downloaded here https://chromedriver.chromium.org/downloads, select the version that matches with the version of your Google chrome browser and download the chrome drive for your operating system.
Based on the operating system your using:
- ### Linux  

download the chrome drive and place it in the driver folder of the project (make sure the name of the chrome drive file is "chromedriver")  

- ### Windows  
  
download the chrome drive and place it in the driver folder of the project (make sure the name of the chrome drive file is "chromedriver.exe")
Then head over to robots folder and open BaseRobot and make sure the System.property value is set to "driver/chromedriver.exe"

```
import project into Intellij
Select Add configuration -> plus icon -> Application

Application Configurations
- Name -> set any name
- Main -> select Main class

Select Apply then Ok
select Run -> Run name(name that was set in the application configuration)
```
