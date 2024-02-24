package com.fges.todoapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoFileStorage {

    private final String fileName;

    public TodoFileStorage(String fileName) {
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

    public int listTodos(boolean onlyDone) {
        try {
            FileHandler handler = FileHandlerFactory.createHandler(fileName);
            handler.list(onlyDone);
            return 0;
        } catch (IOException e) {
            System.err.println("Error while listing TODOs: " + e.getMessage());
            return 1;
        }
    }

    public List<String> getAllTodos() throws IOException {
        FileHandler handler = FileHandlerFactory.createHandler(fileName);
        List<String> todos = new ArrayList<>();
        handler.list(false); // Get all todos
        return todos;
    }
}
