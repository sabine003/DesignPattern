package com.fges.todoapp;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


interface FileHandler {
    String readFile(String fileName) throws IOException;

    void writeFile(String fileName, String content) throws IOException;
}

class JsonFileHandler implements FileHandler {
    public String readFile(String fileName) throws IOException {
        return Files.readString(Path.of(fileName));
    }

    public void writeFile(String fileName, String content) throws IOException {
        Files.write(Path.of(fileName), content.getBytes());
    }
}

class CsvFileHandler implements FileHandler {
    public String readFile(String fileName) throws IOException {
        return Files.readString(Path.of(fileName));
    }

    public void writeFile(String fileName, String content) throws IOException {
        Files.write(Path.of(fileName), content.getBytes());
    }
}

interface Command {
    int execute(String[] args);
}

class InsertCommand implements Command {
    private final FileHandler fileHandler;

    public InsertCommand(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public int execute(String[] args) {
        if (args.length < 3) {
            System.err.println("Missing TODO name");
            return 1;
        }

        String todo = args[2];
        String fileName = args[0];

        try {
            String fileContent = fileHandler.readFile(fileName);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(fileContent);

            if (actualObj instanceof MissingNode) {
                actualObj = JsonNodeFactory.instance.arrayNode();
            }

            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.add(todo);
            }

            fileHandler.writeFile(fileName, actualObj.toString());
            System.err.println("Task inserted.");
            return 0;
        } catch (IOException e) {
            System.err.println("Error reading or writing to the file: " + e.getMessage());
            return 1;
        }
    }
}

class ListCommand implements Command {
    private final FileHandler fileHandler;

    public ListCommand(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public int execute(String[] args) {
        String fileName = args[0];

        try {
            String fileContent = fileHandler.readFile(fileName);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(fileContent);

            if (actualObj instanceof MissingNode) {
                actualObj = JsonNodeFactory.instance.arrayNode();
            }

            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.forEach(node -> System.out.println("- " + node.toString()));
            }

            System.err.println("Listed tasks.");
            return 0;
        } catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
            return 1;
        }
    }
}

public class App {
    /**
     * Do not change this method
     */
    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    public static int exec(String[] args) {
        if (args.length < 2) {
            System.err.println("Missing command or file name");
            return 1;
        }

        String command = args[1];
        String fileName = args[0];

        FileHandler fileHandler;
        if (fileName.endsWith(".json")) {
            fileHandler = new JsonFileHandler();
        } else if (fileName.endsWith(".csv")) {
            fileHandler =  new CsvFileHandler();
        } else {
            System.err.println("Unsupported file format");
            return 1;
        }

        Command commandExecutor;
        if (command.equals("insert")) {
            commandExecutor = new InsertCommand(fileHandler);
        } else if (command.equals("list")) {
            commandExecutor = new ListCommand(fileHandler);
        } else {
            System.err.println("Unknown command");
            return 1;
        }

        return commandExecutor.execute(args);
    }
}
