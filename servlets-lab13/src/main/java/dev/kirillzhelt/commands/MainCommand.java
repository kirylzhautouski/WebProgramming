package dev.kirillzhelt.commands;

import dev.kirillzhelt.commands.params.CommandParams;
import dev.kirillzhelt.db.DatabaseStorage;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Command for home page
 */
public class MainCommand implements GetCommand {

    private DatabaseStorage storage = DatabaseStorage.getInstance();

    @Override
    public void executeGet(CommandParams params) throws ServletException, IOException {
        params.getRequest().getSession().setAttribute("facultyNames", storage.getFacultyDao().getAllFacultyNames());

        params.getRequest().getRequestDispatcher("/views/index.jsp").forward(params.getRequest(), params.getResponse());
    }
}
