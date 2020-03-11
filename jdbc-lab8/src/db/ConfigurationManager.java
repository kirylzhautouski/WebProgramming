package db;

import java.util.ResourceBundle;

/**
 * Class used to retrieve configuration info from properties file
 */
public class ConfigurationManager {

    /** Database url */
    private final String url;

    /** Database driver */
    private final String driver;

    /** Database user */
    private final String user;

    /** Database user's password */
    private final String password;

    /**
     * Constructs a newly created object and retrieves database configuration information
     */
    private ConfigurationManager() {
        ResourceBundle resource = ResourceBundle.getBundle("database");

        this.url = resource.getString("url");
        this.driver = resource.getString("driver");
        this.user = resource.getString("user");
        this.password = resource.getString("password");
    }

    /** The only instance */
    private static ConfigurationManager INSTANCE = null;

    /**
     * Getter for the only instance
     * @return ConfigurationManager
     */
    public static ConfigurationManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfigurationManager();
        }

        return INSTANCE;
    }

    /**
     * Getter for the url
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Getter for the driver
     * @return String
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Getter for the user
     * @return String
     */
    public String getUser() {
        return user;
    }

    /**
     * Getter for the password
     * @return String
     */
    public String getPassword() {
        return password;
    }
}
