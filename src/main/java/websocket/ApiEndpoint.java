package websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Robin
 */
@ServerEndpoint(value = "/kweetEndpoint", encoders = KweetEncoder.class, decoders = KweetDecoder.class)
public class ApiEndpoint {
    
    @OnOpen
    public void onOpen(Session client) {
        // Store new connection in Map/Set
    }

    @OnClose
    public void onClose(Session client) {
        // Remove connection from Map/Set
    }

    @OnError
    public void onError(Throwable t) {
        // Log error
    }

    @OnMessage
    public void onMessage(final Session client, final String message) {
        if (message != null) {
            System.out.println(message);
            // Handle received message
        }
    }
}
