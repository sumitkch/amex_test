package com.amex;

import com.amex.service.Command;
import com.amex.util.CommandParser;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AmexMain {

    private static final String FILE="tmp/commands.txt";
    public static void main(String[] args) {
        var scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            try {
                CommandParser.parse(FILE).stream().filter(Command::shouldRunParser).forEach(Command::execute);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 0, 1, TimeUnit.MINUTES);
    }
}
