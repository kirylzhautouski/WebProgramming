package organization;

import client.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing an operator from an organization. An operator serves clients.
 * Can serve one client at a time.
 */
public class Operator {

    /**
     * Logger for Operator class
     */
    private static final Logger OPERATOR_LOGGER = LogManager.getLogger(Operator.class.getName());

    /**
     * Operator's name
     */
    private final String name;

    /**
     * Initializes a newly created object with the given values
     * @param name
     *        Operator's name
     */
    public Operator(String name) {
        this.name = name;
    }

    /**
     * Getter for Operator's name
     * @return Operator's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Serves client by sleeping for 6 sec, calls callEndCallback at the end of work,
     * either by interruption or not
     * @param client Client to serve
     * @param callEndCallback Called at the end
     */
    public synchronized void serve(Client client, CallEndCallback callEndCallback) {
        try {
            OPERATOR_LOGGER.info("Operator " + this.getName() + " starts serving " + client.getName());

            Thread.sleep(100);

            OPERATOR_LOGGER.info("Operator " + this.getName() + " served " + client.getName());
        } catch (InterruptedException ex) {
            OPERATOR_LOGGER.warn("Operator " + this.getName() + " and Client " + this.getName() +
                " are interrupted");

            Thread.currentThread().interrupt();
        } finally {
            OPERATOR_LOGGER.info("Operator" + this.getName() + " stops serving");

            callEndCallback.onCallEnd(this);
        }
    }
}
