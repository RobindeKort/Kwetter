package websocket;

import com.google.gson.Gson;
import domain.Kweet;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Robin
 */
public class KweetEncoder implements Encoder.Text<Kweet> {
    
    private final Gson gson = new Gson();

    @Override
    public String encode(Kweet kweet) throws EncodeException {
        return gson.toJson(kweet);
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
