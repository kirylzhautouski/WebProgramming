package dev.kirillzhelt.commands;

import dev.kirillzhelt.commands.params.CommandParams;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Base interface for commands that work on POST requests
 */
public interface PostCommand extends Command {

    void executePost(CommandParams params) throws ServletException, IOException;

}
