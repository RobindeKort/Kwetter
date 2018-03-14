package dal;

import domain.Kweet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Robin
 */
public class KweetDaoMem implements IKweetDao {
    private Map<Long, Kweet> kweets;
    
    public KweetDaoMem() {
        kweets = new HashMap();
    }

    @Override
    public void createKweet(Kweet kweet) {
        Long id = new Long(kweets.size());
        kweet.setId(id);
        kweets.putIfAbsent(id, kweet);
    }

    @Override
    public void updateKweet(Kweet kweet) {
        if (kweets.containsKey(kweet.getId())) {
            kweets.put(kweet.getId(), kweet);
        }
        throw new IllegalArgumentException(String.format("Kweet with ID %d does not exist.", kweet.getId()));
    }

    @Override
    public void removeKweet(Kweet kweet) {
        kweets.remove(kweet.getId());
    }
    
    @Override
    public Kweet getKweet(Long id) {
        return kweets.get(id);
    }
}
