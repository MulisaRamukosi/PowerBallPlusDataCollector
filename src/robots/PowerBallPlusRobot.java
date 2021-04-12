package robots;

import models.PowerBallPlusResult;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PowerBallPlusRobot extends BaseRobot{

    private static final PowerBallPlusRobot instance = new PowerBallPlusRobot();

    private final String POWER_BALL_LINK = "https://www.nationallottery.co.za/powerball-plus-history";


    public static PowerBallPlusRobot getInstance() {
        return instance;
    }

    public PowerBallPlusRobot() {
        super();
        navigateTo(POWER_BALL_LINK);
    }

    public void collectDataInDateRange(String start, String end) throws Exception{
        Date startDate = SDF.parse(start);
        Date endDate = SDF.parse(end);

        if (startDate.compareTo(endDate) > 0){
            throw new Exception("start date cannot come after end date");
        }

        FileWriter resultsWriter = createFileWriter("PowerBallPlusResults");
        resultsWriter.write("dawNum,date,result,bonus\n");

        WebElement startDateInput = findElementByXpath("//*[@id=\"fromDate\"]");
        WebElement endDateInput = findElementByXpath("//*[@id=\"toDate\"]");
        WebElement searchBtn = findElementByXpath("//*[@id=\"search\"]");

        String valueFormat = "arguments[0].value = '%s';";

        JavascriptExecutor javascriptExecutor = getJavaScriptExecutor();
        javascriptExecutor.executeScript(String.format(valueFormat, start), startDateInput);
        javascriptExecutor.executeScript(String.format(valueFormat, end), endDateInput);

        searchBtn.click();

        //get reference to pagination
        //used to check if there are results in the page or not
        WebElement pagination = findElementByXpath("//*[@id=\"pag\"]");
        List<WebElement> paginationOpt = pagination.findElements(By.className("footable-page"));

        if (paginationOpt.size() != 0){
            WebElement next;

            do {
                saveResult(resultsWriter);
                findElementByXpath("//*[@id=\"next\"]/a").click();

                //reinitialize next element to check if there are changes in the html
                next = findElementByXpath("//*[@id=\"next\"]");
            }
            while (!next.getAttribute("class").contains("disabled"));
        }

        saveResult(resultsWriter);

        resultsWriter.close();
    }

    public void saveResult(FileWriter resultsWriter) throws IOException {
        WebElement resultsTable = findElementByXpath("//*[@id=\"powerball-results\"]/div[2]/div[1]/div/div[2]");
        String resultTableContent = resultsTable.getAttribute("innerText").trim();

        if (!resultTableContent.isEmpty()){
            String[] results = resultTableContent.split("POWERBALL PLUS DRAW ");

            for (String result : results){
                String[] resultData = result.split("\n");
                if (resultData.length < 7) continue;

                /*
                * account for different screen sizes, depending on the screen size, the results can contain a column
                * called game type, which then affects the index of the data we want to collect, to account for this issue
                * I check the length of the result, if the length is 10, then game type column is there and I have to shift
                * indexing up by 1
                * */
                int i = resultData.length == 10 ? 1 : 0;

                String drawNum = resultData[0];
                String drawDate = resultData[1];
                String drawResults = String.format("%s %s %s %s %s", resultData[2 + i], resultData[3 + i], resultData[4 + i],
                        resultData[5 + i], resultData[6 + i]);
                String bonus = resultData[8 + i];

                PowerBallPlusResult powerBallPlusResult = new PowerBallPlusResult(drawNum, drawDate, drawResults, bonus);
                String drawResult = powerBallPlusResult.toString();
                resultsWriter.write(drawResult);
                resultsWriter.append('\n');

            }
        }

    }
}
