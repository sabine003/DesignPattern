package com.fges.todoapp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.List;

public class CommandLineExecutor {

    public int execute(CommandLineParser parser, String[] args) throws Exception {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");
        cliOptions.addOption("d", "done", false, "Mark todo as done");

        CommandLine cmd;
        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            System.err.println("Failed to parse arguments: " + ex.getMessage());
            return 1;
        }

        String fileName = cmd.getOptionValue("s");
        List<String> positionalArgs = cmd.getArgList();

        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String command = positionalArgs.get(0);
        TodoFileExec fileExec = new TodoFileExec(fileName);

        if (command.equals("insert")) {
            boolean markAsDone = cmd.hasOption("d"); // Check if --done flag is present
            return insertTodo(positionalArgs, fileExec, markAsDone);
        } else if (command.equals("list")) {
            return listTodos(fileExec);
        } else {
            System.err.println("Invalid command: " + command);
            return 1;
        }
    }

    private int insertTodo(List<String> positionalArgs, TodoFileExec fileExec, boolean markAsDone) {
        if (positionalArgs.size() < 2) {
            System.err.println("Missing TODO name");
            return 1;
        }

        String todo = positionalArgs.get(1);
        return fileExec.insertTodo(todo, markAsDone);
    }

    private int listTodos(TodoFileExec fileExec) {
        return fileExec.listTodos();
    }
}
