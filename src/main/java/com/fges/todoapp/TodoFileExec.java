package com.fges.todoapp;

import java.io.IOException;

public class TodoFileExec {

    private final String fileName;

    public TodoFileExec(String fileName) {
        this.fileName = fileName;
    }

    public int insertTodo(String todo, boolean markAsDone) {
        try {
            FileHandler handler = FileHandlerTest.createHandler(fileName);
            handler.insert(todo, markAsDone);
            return 0;
        } catch (IOException e) {
            System.err.println("Error while inserting TODO: " + e.getMessage());
            return 1;
        }
    }

    public int listTodos() {
        try {
            FileHandler handler = FileHandlerTest.createHandler(fileName);
            handler.list();
            return 0;
        } catch (IOException e) {
            System.err.println("Error while listing TODOs: " + e.getMessage());
            return 1;
        }
    }
}