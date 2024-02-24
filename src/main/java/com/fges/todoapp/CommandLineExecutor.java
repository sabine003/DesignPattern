package com.fges.todoapp;

import org.apache.commons.cli.*;

import java.util.List;

public class CommandLineExecutor {

    public int execute(CommandLineParser parser, String[] args) throws Exception {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");
        cliOptions.addOption("d", "done", false, "Mark todo as done");
        cliOptions.addOption("o", "output", true, "Output file for migration todos");

        CommandLine cmd = parser.parse(cliOptions, args);

        String fileName = cmd.getOptionValue("s");
        boolean markAsDone = cmd.hasOption("d");
        List<String> positionalArgs = cmd.getArgList();

        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String commandKey = positionalArgs.get(0);
        TodoFileStorage fileExec = new TodoFileStorage(fileName);
        Command command;

        switch (commandKey) {
            case "insert":
                command = new InsertCommand(fileExec, markAsDone);
                break;
            case "list":
                command = new ListCommand(fileExec, markAsDone);
                break;
            case "migrate":
                String outputFileName = cmd.getOptionValue("o");
                if (fileName == null || outputFileName == null) {
                    System.err.println("Both source and output files must be provided for migrate command");
                    return 1;
                }
                TodoFileStorage sourceFileExec = new TodoFileStorage(fileName);
                command = new MigrateCommand(sourceFileExec, outputFileName);
                break;
            default:
                System.err.println("Invalid command: " + commandKey);
                return 1;
        }

        return command.execute(positionalArgs.subList(1, positionalArgs.size()));
    }
}

