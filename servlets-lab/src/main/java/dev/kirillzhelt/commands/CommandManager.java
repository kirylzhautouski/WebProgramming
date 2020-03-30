package dev.kirillzhelt.commands;

import dev.kirillzhelt.commands.enrollees.AllEnrolleesCommand;
import dev.kirillzhelt.commands.enrollees.AppliedEnrolleesCommand;
import dev.kirillzhelt.commands.enrollees.EnrolleesAboveAverageCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * This manager used to map url to handling command
 */
public abstract class CommandManager {

    public static final String MAIN_PAGE_URL = "/";
    public static final String ALL_ENROLLEES_URL = "/all-enrollees";
    public static final String APPLIED_ENROLLEES_URL = "/applied-enrollees";
    public static final String AVERAGE_GRADE_URL = "/average-grade";
    public static final String ENROLLEES_ABOVE_AVERAGE_URL = "/above-average";
    public static final String REGISTER_ENROLLEE_URL = "/register-enrollee";

    private static final Command mainCommand = new MainCommand();

    private static final Map<String, Command> URLS_TO_COMMANDS = new HashMap<String, Command>() {{
        put(MAIN_PAGE_URL, mainCommand);
        put(ALL_ENROLLEES_URL, new AllEnrolleesCommand());
        put(APPLIED_ENROLLEES_URL, new AppliedEnrolleesCommand());
        put(AVERAGE_GRADE_URL, new AverageGradeCommand());
        put(ENROLLEES_ABOVE_AVERAGE_URL, new EnrolleesAboveAverageCommand());
        put(REGISTER_ENROLLEE_URL, new RegisterEnrolleeCommand());
    }};

    private CommandManager() {

    }

    public static Command getCommandForURL(String url) {
        return CommandManager.URLS_TO_COMMANDS.getOrDefault(url, mainCommand);
    }

}
