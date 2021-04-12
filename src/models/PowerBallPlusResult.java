package models;

public class PowerBallPlusResult extends BaseResult{

    private final String bonus;

    public PowerBallPlusResult(String drawNum, String drawDate, String result, String bonus) {
        super(drawNum, drawDate, result);
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", super.toString(), bonus);
    }
}
