package com.amex.service;

import com.amex.exception.InvalidCommandInputException;
import static com.amex.util.CommandParser.stringToInt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class OneTimeCommand implements Command{
    private final String message;
    private final LocalDateTime scheduledTime;
    private boolean executed = false;

    public OneTimeCommand(String userCommand) {
        String[] cmdAndMsg = userCommand.split(" ");
        if(cmdAndMsg.length < 6)
            throw new InvalidCommandInputException("Invalid Command provided in file and Command : %s".formatted(userCommand));
        this.message = Arrays.stream(cmdAndMsg).skip(5).collect(Collectors.joining(" "));
        this.scheduledTime = LocalDateTime.of(stringToInt(cmdAndMsg[4]),
                stringToInt(cmdAndMsg[3]),
                stringToInt(cmdAndMsg[2]),
                stringToInt(cmdAndMsg[1]),
                stringToInt(cmdAndMsg[0]));
    }
    @Override
    public boolean shouldRunParser() {
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
        return !executed && now.equals(scheduledTime);
    }

    @Override
    public void execute() {
        executed = true;
        runShellCommand(message);
    }
}
