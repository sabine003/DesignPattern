package com.fges.todoapp;

import org.apache.commons.cli.*;

import java.util.List;

public class CommandLineExecutor {

    public int execute(CommandLineParser parser, String[] args) throws Exception {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");
        cliOptions.addOption("d", "done", false, "Mark todo as done");

        CommandLine cmd = parser.parse(cliOptions, args);
        String fileName = cmd.getOptionValue("s");
        boolean markAsDone = cmd.hasOption("d");
        List<String> positionalArgs = cmd.getArgList();

        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String commandKey = positionalArgs.get(0);
        TodoFileExec fileExec = new TodoFileExec(fileName);
        Command command;

        switch (commandKey) {
            case "insert":
                command = new InsertCommand(fileExec, markAsDone);
                break;
            case "list":
                command = new ListCommand(fileExec, markAsDone);
                break;
            default:
                System.err.println("Invalid command: " + commandKey);
                return 1;
        }

        return command.execute(positionalArgs.subList(1, positionalArgs.size()));
    }
}

