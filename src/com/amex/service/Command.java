package com.amex.service;

public sealed interface Command permits OneTimeCommand, RecurringCommand {
    boolean shouldRunParser();
    void execute();

     default void runShellCommand(String cmd) {
        try {
            ProcessBuilder builder = new ProcessBuilder("sh", "-c", cmd);
            builder.inheritIO();
            Process process = builder.start();
            process.waitFor();
        } catch (Exception e) {
            System.err.println("Execution failed: " + e.getMessage());
        }
    }

}
