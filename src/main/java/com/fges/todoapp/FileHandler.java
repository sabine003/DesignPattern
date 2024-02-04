package com.fges.todoapp;

import java.io.IOException;

public interface FileHandler {

    void insert(String todo, boolean markAsDone) throws IOException;
    void list() throws IOException;
}
