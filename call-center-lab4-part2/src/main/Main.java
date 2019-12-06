package main;

import client.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import organization.Operator;
import organization.Organization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main class containing an entry point of an application - main method
 */
public class Main {

    /**
     * Logger for Main class
     */
    private static final Logger MAIN_LOGGER = LogManager.getLogger(Main.class.getName());

    private static final int NUMBER_OF_CLIENTS = 20;
    private static final int NUMBER_OF_OPERATORS = 6;

    /**
     * Testing method for drop call functionality
     */
    private static void testDropCall() {
        MAIN_LOGGER.info("testDropCall started");

        MAIN_LOGGER.info("Creating operators");
        List<Operator> operators = new ArrayList<Operator>() {{
            add(new Operator("Operator1"));
        }};

        MAIN_LOGGER.info("Creating organization");
        Organization organization = new Organization(operators);

        MAIN_LOGGER.info("Creating clients");
        Client client1 = new Client("Client1");
        Client client2 = new Client("Client2");

        try {
            MAIN_LOGGER.info("Clients are calling");

            client1.call(organization);
            client2.call(organization);

            Thread.sleep(500);
            client2.dropCall();

            client1.join();
            client2.join();
        } catch (InterruptedException ex) {
            MAIN_LOGGER.warn(ex.getMessage());
        }
    }

    /**
     * Testing method for calling with many operators and many clients
     */
    private static void testCallingWithManyOperatorsAndManyClients() {
        MAIN_LOGGER.info("testCallingWithManyOperatorsAndManyClients started");

        MAIN_LOGGER.info("Creating operators");

        Operator[] operators = new Operator[NUMBER_OF_OPERATORS];
        for (int i = 0; i < NUMBER_OF_OPERATORS; i++)
            operators[i] = new Operator("Operator" + i);

        MAIN_LOGGER.info("Creating organization");
        Organization organization = new Organization(Arrays.asList(operators));

        MAIN_LOGGER.info("Creating clients");

        Client[] clients = new Client[NUMBER_OF_CLIENTS];
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++)
            clients[i] = new Client("Client" + i);

        try {
            MAIN_LOGGER.info("Clients are calling");

            for (int i = 0; i < NUMBER_OF_CLIENTS; i++)
                clients[i].call(organization);

            for (int i = 0; i < NUMBER_OF_CLIENTS; i++)
                clients[i].join();
        } catch (InterruptedException ex) {
            MAIN_LOGGER.warn(ex.getMessage());
        }
    }

    /**
     * An entry point of an application
     * @param args
     *        Command-line args
     */
    public static void main(String[] args) {
        MAIN_LOGGER.info("Main started");

        testDropCall();

        testCallingWithManyOperatorsAndManyClients();
    }

}
