package dev.kirillzhelt.commands;

import dev.kirillzhelt.commands.params.CommandParams;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Command for home page
 */
public class MainCommand implements GetCommand {

    @Override
    public void executeGet(CommandParams params) throws ServletException, IOException {
        params.getRequest().getSession().setAttribute("facultyNames", params.getFacultyDao().getAllFacultyNames());

        params.getRequest().getRequestDispatcher("/views/index.jsp").forward(params.getRequest(), params.getResponse());
    }
}
