package util;

/**
 *
 * @author Robin
 */
public class PropertiesProvider {

    private static String securityKey = "treehunter";

    // TODO robkor: replace with actual properties file
    public static String getSecurityKey() {
        return securityKey;
    }
}
