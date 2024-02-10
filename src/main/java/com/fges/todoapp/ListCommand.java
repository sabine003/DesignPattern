package com.fges.todoapp;

import java.io.IOException;
import java.util.List;

public class ListCommand implements Command {
    private final TodoFileExec fileExec;
    private final boolean onlyDone;

    public ListCommand(TodoFileExec fileExec, boolean onlyDone) {
        this.fileExec = fileExec;
        this.onlyDone = onlyDone;
    }

    @Override
    public int execute(List<String> args) throws IOException {
        return fileExec.listTodos(onlyDone);
    }
}
