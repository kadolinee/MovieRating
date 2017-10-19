package com.epam.kinorating.command;

public class ActionFactory {
    public static Command defineCommand(String commandName) {
        Command current;

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(commandName.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            current = new EmptyCommand();
        }

        return current;
    }
}
