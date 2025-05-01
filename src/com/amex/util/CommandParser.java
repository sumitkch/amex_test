package com.amex.util;

import com.amex.service.Command;
import com.amex.service.OneTimeCommand;
import com.amex.service.RecurringCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class CommandParser {

    private static final String HASH = "#";

    public static List<Command> parse(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath)).stream()
                .filter(Predicate.not(line -> line.isBlank() || line.startsWith(HASH)))
                .map(line -> line.startsWith("*/") ? new RecurringCommand(line) : new OneTimeCommand(line)).toList();
    }

    public static int stringToInt(String s){
        return Integer.parseInt(s);
    }
}
