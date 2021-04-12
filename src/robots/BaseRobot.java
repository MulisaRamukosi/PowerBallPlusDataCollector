package robots;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Constants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class BaseRobot {

    private final WebDriver webDriver;
    protected final SimpleDateFormat SDF = new SimpleDateFormat(Constants.RESULT_DATE_FORMAT);

    public BaseRobot(){
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        webDriver = new ChromeDriver();
    }

    public void navigateTo(String link){
        webDriver.navigate().to(link);
    }

    public WebElement findElementById(String id){
        return webDriver.findElement(By.id(id));
    }

    public WebElement findElementByXpath(String xPath){
        return webDriver.findElement(By.xpath(xPath));
    }

    //used to execute javascript specific code
    public JavascriptExecutor getJavaScriptExecutor(){
        return (JavascriptExecutor) webDriver;
    }

    public FileWriter createFileWriter(String fileName) throws IOException {
        fileName = String.format("%s.csv", fileName);
        return new FileWriter(fileName, true);
    }
}
