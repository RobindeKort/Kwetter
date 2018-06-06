package websocket;

import com.google.gson.Gson;
import domain.Kweet;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Robin
 */
public class KweetDecoder implements Decoder.Text<Kweet> {
    
    private final Gson gson = new Gson();

    @Override
    public Kweet decode(String s) throws DecodeException {
        return gson.fromJson(s, Kweet.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
