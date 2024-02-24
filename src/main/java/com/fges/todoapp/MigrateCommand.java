package com.fges.todoapp;
import java.io.IOException;
import java.util.List;

public class MigrateCommand implements Command {
    private final TodoFileExec sourceFileExec;
    private final String outputFileName;

    public MigrateCommand(TodoFileExec sourceFileExec, String outputFileName) {
        this.sourceFileExec = sourceFileExec;
        this.outputFileName = outputFileName;
    }

    @Override
    public int execute(List<String> args) throws IOException {
        try {
            List<String> sourceTodos = sourceFileExec.getAllTodos();

            TodoFileExec outputFileExec = new TodoFileExec(outputFileName);
            List<String> existingTodos = outputFileExec.getAllTodos();


            for (String todo : sourceTodos) {
                outputFileExec.insertTodo(todo, false);
            }

            return 0;
        } catch (IOException e) {
            System.err.println("Error while migrating todos: " + e.getMessage());
            return 1;
        }
    }
}
