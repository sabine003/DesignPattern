package com.fges.todoapp;

import java.io.IOException;
import java.util.List;

public interface Command {
    int execute(List<String> args) throws IOException;
}
