package com.amex.service;

import com.amex.exception.InvalidCommandInputException;
import com.amex.util.CommandParser;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class RecurringCommand implements Command{
    private final String message;
    private final int intervalMinutes;


    public RecurringCommand(String userCommand){
        String[] cmdAndMsg = userCommand.split(" ");
        if(cmdAndMsg.length < 2)
            throw new InvalidCommandInputException("Invalid Command provided in file and Command : %s".formatted(userCommand));
        this.message = Arrays.stream(cmdAndMsg).skip(1).collect(Collectors.joining(" "));
        this.intervalMinutes= CommandParser.stringToInt(cmdAndMsg[0].substring(2));
    }
    @Override
    public boolean shouldRunParser() {
        int currentMinute = LocalDateTime.now().getMinute();
        return currentMinute % intervalMinutes == 0;
    }

    @Override
    public void execute() {

        runShellCommand(message);
    }
}
