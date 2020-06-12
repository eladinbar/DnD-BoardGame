package Model.UnitPackage;

public class CombatResult {
    private Unit unit;
    public String output;

    public CombatResult(Unit unit, String output) {
        this.unit = unit;
        this.output = output;
    }

    public CombatResult(String output) {
        this.unit = null;
        this.output = "";
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
