package netty.model.request;

/**
 * Class that represents Request information
 */
public class RequestData {

    /** Request string from client */
    private String request;

    /**
     * Getter for request
     * @return String
     */
    public String getRequest() {
        return request;
    }

    /**
     * Setter for request
     * @param request Request
     */
    public void setRequest(String request) {
        this.request = request;
    }
}
