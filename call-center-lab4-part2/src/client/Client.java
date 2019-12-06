package client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import organization.Organization;

/**
 * Class representing a client. A client calls an organization in a newly created thread.
 * A client can drop a call.
 */
public class Client implements Runnable {

    /**
     * Logger for Client class
     */
    private final static Logger CLIENT_LOGGER = LogManager.getLogger(Client.class.getName());

    /** Client's name */
    private final String name;

    /** Organization to call */
    private Organization organization;

    /** Thread used to call */
    private Thread callThread;

    /**
     * Initializes a newly created Client object with the given values
     * @param name
     *        Client's name
     */
    public Client(String name) {
        this.name = name;
    }

    /**
     * Getter for Client's name
     * @return  Client's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Call organization in a newly created thread
     * @param organization Organization
     */
    public void call(Organization organization) {
        CLIENT_LOGGER.info(this.getName() + " started calling");

        this.organization = organization;

        callThread = new Thread(this);
        callThread.start();
    }

    /**
     * Drops call, if callThread is not null
     */
    public void dropCall() {
        CLIENT_LOGGER.info(this.getName() + " is dropping call");

        if (callThread != null)
            callThread.interrupt();
    }

    /**
     * Joins Client's callThread
     * @throws InterruptedException
     */
    public void join() throws InterruptedException {
        if (callThread != null)
            callThread.join();
    }

    /**
     * Implementation of the runnable interface
     */
    @Override
    public void run() {
        organization.call(this);
    }
}
