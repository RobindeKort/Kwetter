package rest;

import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import util.PropertiesProvider;

/**
 *
 * @author Robin
 */
@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        // Get the HTTP Authorization header from the request
        Cookie cookie = crc.getCookies().get("access_token");

        try {
            String token = cookie.getValue();
            // Validate the token
            String key = PropertiesProvider.getSecurityKey();
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        } catch (Exception e) {
            crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
