package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CsvFileHandler implements FileHandler {

    private final String fileName;

    public CsvFileHandler(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void insert(String todo, boolean markAsDone) throws IOException {
        Path filePath = Paths.get(fileName);
        String fileContent = "";

        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }

        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }

        fileContent += "\"" + todo + "\"," + markAsDone;

        Files.writeString(filePath, fileContent);
    }

    @Override
    public void list(boolean onlyDone) throws IOException {
        Path filePath = Paths.get(fileName);

        if (Files.exists(filePath)) {
            Files.lines(filePath).forEach(line -> {
                String[] parts = line.split(",");
                boolean done = Boolean.parseBoolean(parts[1]);
                if (!onlyDone || done) {
                    String output = done ? "Done: " : "";
                    System.out.println("- " + output + parts[0].replaceAll("\"", ""));
                }
            });
        }
    }
}
