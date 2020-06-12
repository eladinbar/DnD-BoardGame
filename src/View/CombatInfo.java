package View;

public class CombatInfo implements GameInfo {
    private  String combatResult;

    public CombatInfo(String result){
        combatResult = result;
    }

    @Override
    public void printInfo() {
        if(!combatResult.isEmpty()){
            System.out.println(combatResult);
        }
    }
}
