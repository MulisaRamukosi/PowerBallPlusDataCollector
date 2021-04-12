import robots.PowerBallPlusRobot;

public class Main {

    public static void main(String[] args){
        PowerBallPlusRobot powerBallPlusRobot = PowerBallPlusRobot.getInstance();

        final String START_DATE = "01/12/2020";
        final String END_DATE = "11/04/2021";

        try {
            powerBallPlusRobot.collectDataInDateRange(START_DATE, END_DATE);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
