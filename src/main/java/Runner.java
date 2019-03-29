package main.java;

import java.io.File;

public class Runner extends Solver {
    public Runner(Board initial) {
        super(initial);
    }

    public static void main(String[] args) {
        File folder = new File("boards");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String name = file.getName();
                System.out.println(name);
                Solver.main(new String[]{"boards/".concat(name)});
            }
        }
    }
}