package organization;

import client.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an organization. Organization holds operators and distributes them between clients.
 */
public class Organization implements CallEndCallback {

    /**
     * Logger for Organization class
     */
    private final static Logger ORGANIZATION_LOGGER = LogManager.getLogger(Organization.class.getName());

    /**
     * List of operators of this organization, used as a synchronization object
     */
    private final List<Operator> operators;

    /**
     * Initializes a newly constructed object with the given operators
     * @param operators
     *        Operators
     */
    public Organization(List<Operator> operators) {
        this.operators = new ArrayList<>(operators);
    }

    /**
     * Distributes an operator for a client. If there are no free operators, a client starts to wait.
     * When some operator ended serving, notify is called from CallEndCallback interface.
     * @param client
     *        Client
     */
    public void call(Client client) {
        try {
            ORGANIZATION_LOGGER.info(client.getName() + " is calling");

            Operator operatorToServe = null;
            synchronized (operators) {
                while (operatorToServe == null) {
                    for (Operator operator : operators) {
                        ORGANIZATION_LOGGER.info(client.getName() + " checks " + operator.getName());

                        if (!operator.isServing()) {
                            operator.prepareToServe();
                            operatorToServe = operator;

                            break;
                        }
                    }

                    if (operatorToServe == null) {
                        ORGANIZATION_LOGGER.info(client.getName() + " waits for free operator");

                        operators.wait();
                    }
                }
            }

            ORGANIZATION_LOGGER.info(operatorToServe.getName() + " will serve" + client.getName());
            operatorToServe.serve(client, this);
        } catch (InterruptedException ex) {
            ORGANIZATION_LOGGER.warn(client.getName() + " is interrupted");

            Thread.currentThread().interrupt();
        }
    }

    /**
     * Implementation of CallEndCallbackInterface. Used to show that operator is free now,
     * and notify waiting clients about it
     */
    @Override
    public void onCallEnd() {
        ORGANIZATION_LOGGER.info("Call ended, notifying other clients, that operator is free");

        synchronized (operators) {
            operators.notify();
        }
    }
}
