package com.fges.todoapp;

import java.io.IOException;

public class TodoFileManager {

    private final String fileName;

    public TodoFileManager(String fileName) {
        this.fileName = fileName;
    }

    public int insertTodo(String todo, boolean markAsDone) {
        try {
            FileHandler handler = FileHandlerFactory.createHandler(fileName);
            handler.insert(todo, markAsDone);
            return 0;
        } catch (IOException e) {
            System.err.println("Error while inserting TODO: " + e.getMessage());
            return 1;
        }
    }

    public int listTodos() {
        try {
            FileHandler handler = FileHandlerFactory.createHandler(fileName);
            handler.list();
            return 0;
        } catch (IOException e) {
            System.err.println("Error while listing TODOs: " + e.getMessage());
            return 1;
        }
    }
}
