import Controller.CommandLineInterface;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> levelsPath = getLevelPaths(args[0]);
        CommandLineInterface CLI = new CommandLineInterface(levelsPath);
        CLI.start();
        CLI.play();
    }

    public static ArrayList<String> getLevelPaths(String folderPath){
        File levelFolder = new File(folderPath);
        String[] folderContent = levelFolder.list();
        ArrayList<String> levelsPath = new ArrayList<>();
        for (String content : folderContent) {
            levelsPath.add(folderPath + "\\" + content);
        }
        return levelsPath;
    }
}
