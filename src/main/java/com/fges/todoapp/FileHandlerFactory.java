package com.fges.todoapp;

public class FileHandlerFactory {

    public static FileHandler createHandler(String fileName) {
        if (fileName.endsWith(".json")) {
            return new JsonFileHandler(fileName);
        } else if (fileName.endsWith(".csv")) {
            return new CsvFileHandler(fileName);
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + fileName);
        }
    }
}
