import Controller.CommandLineInterface;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> levelsPath = new ArrayList<>();
        for(int i = 0; i< args.length; i++){
            levelsPath.add(args[i]);
        }

        CommandLineInterface CLI = new CommandLineInterface(levelsPath);
        CLI.start();
        CLI.play();
    }
}
