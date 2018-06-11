package websocket;

import domain.Account;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import rest.JWTTokenNeeded;
import service.AccountService;

/**
 *
 * @author Robin
 */
@ServerEndpoint(value = "/kweetEndpoint/{username}", encoders = KweetEncoder.class, decoders = KweetDecoder.class)
@JWTTokenNeeded
public class ApiEndpoint {

    private AccountService accountService;

    @Inject
    public ApiEndpoint(AccountService accountService) {
        this.accountService = accountService;
    }

    @OnOpen
    public void onOpen(Session client, @PathParam("username") String username) {
        // Store new connection in Map/Set
        SessionMapper.getInstance().getSessionMap().put(username, client);
    }

    @OnClose
    public void onClose(Session client, @PathParam("username") String username) {
        // Remove connection from Map/Set
        SessionMapper.getInstance().getSessionMap().remove(username, client);
    }

    /*@OnError
    public void onError(Throwable t) {
        // Log error
    }*/
    
    @OnMessage
    public void onMessage(final String message, final Session client, @PathParam("username") String username) {
        // Handle received message
        Account sender = accountService.getAccount(username);
        for (Account following : sender.getFollowedBy()) {
            if (SessionMapper.getInstance().getSessionMap().keySet().contains(following.getUserName())) {
                SessionMapper.getInstance().getSessionMap().get(following.getUserName()).getAsyncRemote().sendText(message);
            }
        }
    }
}
