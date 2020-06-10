package Controller;

public enum ActionListInput{

    Up('w'),
    Down('s'),
    Left('a'),
    Right('d'),
    CastAbility('e'),
    Stay('q');

    char keyPress;
    ActionListInput(char c){
        keyPress = c;
    }
}
