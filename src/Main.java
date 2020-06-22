import Controller.CommandLineInterface;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        CommandLineInterface CLI = new CommandLineInterface(args[0]);
        CLI.start();
        CLI.play();
    }


}
