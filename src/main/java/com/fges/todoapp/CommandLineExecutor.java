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

        if (positionalArgs.isEmpty() || !positionalArgs.get(0).equals("insert")) {
            System.err.println("Missing or incorrect command");
            System.out.println("Status code: 1");
            return 1;
        }

        String commandKey = positionalArgs.get(0);
        TodoFileStorage fileExec = new TodoFileStorage(fileName);
        Command command;

        switch (commandKey) {
            case "insert":
                // Construct the todo text from the remaining arguments
                String todo = String.join(" ", positionalArgs.subList(1, positionalArgs.size()));
                command = new InsertCommand(fileExec, markAsDone); // Adjust InsertCommand constructor to accept todo text directly
                break;
            case "list":
                command = new ListCommand(fileExec, markAsDone);
                break;
            case "migrate":
                String outputFileName = cmd.getOptionValue("o");
                if (fileName == null || outputFileName == null) {
                    System.err.println("Both source and output files must be provided for migrate command");
                    System.out.println("Status code: 1");
                    return 1;
                }
                TodoFileStorage sourceFileExec = new TodoFileStorage(fileName);
                command = new MigrateCommand(sourceFileExec, outputFileName);
                break;
            case "web":
                if (fileName == null) {
                    System.err.println("Source file must be specified with -s or --source for the web command");
                    return 1;
                }
                command = new WebCommand(fileName);
                break;
            default:
                System.err.println("Invalid command: " + commandKey);
                System.out.println("Status code: 1");
                return 1;
        }

        int status = command.execute(positionalArgs.subList(1, positionalArgs.size()));
        System.out.println("Status code: " + status);
        return status;
    }
}

