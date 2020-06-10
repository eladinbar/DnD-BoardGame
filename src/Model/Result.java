package Model;

public class Result {
    int diceRoll;
    String output;

    public Result(int diceRoll, String output) {
        this.diceRoll = diceRoll;
        this.output = output;
    }

    public int getDiceRoll() {
        return diceRoll;
    }

    public void setDiceRoll(int diceRoll) {
        this.diceRoll = diceRoll;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
