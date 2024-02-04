package com.fges.todoapp;

import org.apache.commons.cli.*;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        CommandLineExecutor executor = new CommandLineExecutor();

        try {
            int exitCode = executor.execute(parser, args);
            System.err.println("Done");
            System.exit(exitCode);
        } catch (Exception ex) {
            System.err.println("An error occurred: " + ex.getMessage());
            System.exit(1);
        }
    }

    public static int exec(String[] args) throws IOException {
        CommandLineParser parser = new DefaultParser();
        CommandLineExecutor executor = new CommandLineExecutor();

        try {
            return executor.execute(parser, args);
        } catch (Exception ex) {
            System.err.println("An error occurred: " + ex.getMessage());
            return 1;
        }
    }
}
