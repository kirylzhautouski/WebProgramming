package dev.kirillzhelt.db.commands;

import dev.kirillzhelt.db.commands.params.CommandParams;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Base interface for commands that work on POST requests
 */
public interface PostCommand extends Command {

    void executePost(CommandParams params) throws ServletException, IOException;

}
