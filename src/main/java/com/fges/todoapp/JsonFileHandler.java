package com.fges.todoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonFileHandler implements FileHandler {

    private final String fileName;

    public JsonFileHandler(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void insert(String todo, boolean markAsDone) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Path filePath = Paths.get(fileName);
        ArrayNode arrayNode;

        if (Files.exists(filePath)) {
            String fileContent = Files.readString(filePath);
            arrayNode = (ArrayNode) mapper.readTree(fileContent);
        } else {
            arrayNode = JsonNodeFactory.instance.arrayNode();
        }

        ObjectNode newNode = JsonNodeFactory.instance.objectNode();
        newNode.put("text", todo);
        newNode.put("done", markAsDone);
        arrayNode.add(newNode);

        Files.writeString(filePath, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode));
    }

    @Override
    public void list(boolean onlyDone) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Path filePath = Paths.get(fileName);

        if (Files.exists(filePath)) {
            String fileContent = Files.readString(filePath);
            ArrayNode arrayNode = (ArrayNode) mapper.readTree(fileContent);
            arrayNode.forEach(node -> {
                boolean done = node.get("done").asBoolean();
                if (!onlyDone || done) {
                    String output = done ? "Done: " : "";
                    System.out.println("- " + output + node.get("text").asText());
                }
            });
        }
    }
}
