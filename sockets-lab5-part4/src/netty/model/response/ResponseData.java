package netty.model.response;

/**
 * Class that represents ResponseData
 */
public class ResponseData {

    /**
     * Random line from text for client
     */
    private String randomLineFromText;

    /**
     * Gettter for line
     * @return line
     */
    public String getRandomLineFromText() {
        return randomLineFromText;
    }

    /**
     * Setter for line
     * @param randomLineFromText line
     */
    public void setRandomLineFromText(String randomLineFromText) {
        this.randomLineFromText = randomLineFromText;
    }
}
