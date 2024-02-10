package com.fges.todoapp;

import java.io.IOException;
import java.util.List;

public class InsertCommand implements Command {
    private final TodoFileExec fileExec;
    private final boolean markAsDone;

    public InsertCommand(TodoFileExec fileExec, boolean markAsDone) {
        this.fileExec = fileExec;
        this.markAsDone = markAsDone;
    }

    @Override
    public int execute(List<String> args) throws IOException {
        if (args.isEmpty()) {
            System.err.println("Missing TODO name");
            return 1;
        }

        String todo = String.join(" ", args);
        return fileExec.insertTodo(todo, markAsDone);
    }
}
