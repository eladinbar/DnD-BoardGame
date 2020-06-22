package Controller;

//Enum for more readable code and flexibility to change the controls key.
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
