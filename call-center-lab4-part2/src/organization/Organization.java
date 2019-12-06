package organization;

import client.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class representing an organization. Organization holds operators and distributes them between clients.
 */
public class Organization implements CallEndCallback {

    /**
     * Logger for Organization class
     */
    private final static Logger ORGANIZATION_LOGGER = LogManager.getLogger(Organization.class.getName());

    /**
     * Blocking queue of free operators
     */
    private final BlockingQueue<Operator> freeOperators;

    /**
     * Initializes a newly constructed object with the given operators
     * @param operators
     *        Operators
     */
    public Organization(List<Operator> operators) {
        this.freeOperators = new ArrayBlockingQueue<>(operators.size(), false, operators);
    }

    /**
     * Distributes an operator for a client. If there are no free operators, a client starts to wait.
     * When some operator ended serving, notify is called from CallEndCallback interface.
     * @param client
     *        Client
     */
    public void call(Client client) {
        ORGANIZATION_LOGGER.info(client.getName() + " is calling");

        try {
            ORGANIZATION_LOGGER.info(client.getName() + " takes operator from queue");

            Operator operatorToServe = freeOperators.take();

            ORGANIZATION_LOGGER.info(operatorToServe.getName() + " will serve " + client.getName());
            operatorToServe.serve(client, this);

        } catch (InterruptedException ex) {
            ORGANIZATION_LOGGER.warn(client.getName() + " is interrupted");

            Thread.currentThread().interrupt();
        }
    }

    /**
     * Implementation of CallEndCallbackInterface. Adds operator to queue of free operators
     *      @param operator
     *             Operator
     */
    @Override
    public void onCallEnd(Operator operator) {
        ORGANIZATION_LOGGER.info("Call ended, adding to queue, that operator is free");

        try {
            freeOperators.put(operator);
        } catch (InterruptedException ex) {
            ORGANIZATION_LOGGER.warn(operator.getName() + " onCallEnd interrupted");
        }
    }
}
