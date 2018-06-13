package rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Robin
 */
@ApplicationPath("api")
public class KwetterApplication extends Application {
    public static final String BASE_ANG_URL = "http://localhost:4200";
    public static final String BASE_API_URL = "http://localhost:8080/Kwetter/api";
}
