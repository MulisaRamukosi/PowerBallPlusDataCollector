package models;

public class BaseResult {

    private final String drawNum;
    private final String drawDate;
    private final String result;

    public BaseResult(String drawNum, String drawDate, String result) {
        this.drawNum = drawNum;
        this.drawDate = drawDate;
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", drawNum, drawDate, result);
    }
}
