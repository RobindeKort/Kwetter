package websocket;

import java.util.HashMap;
import java.util.Map;
import javax.websocket.Session;

/**
 *
 * @author Robin
 */
public class SessionMapper {
    private static SessionMapper sessionMapper = null;
    private static Map<String, Session> sessions;
    
    private SessionMapper() {
        sessions = new HashMap();
    }
    
    public static SessionMapper getInstance() {
        if (sessionMapper == null) {
            sessionMapper = new SessionMapper();
        }
        return sessionMapper;
    }
    
    public Map<String, Session> getSessionMap() {
        return sessions;
    }
}
