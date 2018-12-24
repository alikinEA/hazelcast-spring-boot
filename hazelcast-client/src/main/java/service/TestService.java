package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.HazelcastInstance;
import config.HazelcastClientInstance;
import model.TestPojo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Alikin E.A. on 17.02.18.
 */
@Service
public class TestService extends HazelcastClientInstance {

    private static final String COLLECTION_NAME = "testReplicatedMap";

    public TestPojo get(Integer id) {
        HazelcastInstance hazelcastInstance = getHZInstance();
        try {
            Map<Integer, String> map = hazelcastInstance.getReplicatedMap(COLLECTION_NAME);
            String cacheStr = map.get(id);
            if (cacheStr == null) {
                return null;
            } else {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(cacheStr, TestPojo.class);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error get", e);
        }
    }

    public TestPojo put(Integer id,TestPojo object) {
        HazelcastInstance hazelcastInstance = getHZInstance();
        try {
            Map<Integer, String> map = hazelcastInstance.getReplicatedMap(COLLECTION_NAME);
            ObjectMapper mapper = new ObjectMapper();
            map.put(id,mapper.writeValueAsString(object));
            return object;
        } catch (Exception e) {
            throw new RuntimeException("Error put", e);
        }
    }

    public TestPojo remove(Integer id) {
        HazelcastInstance hazelcastInstance = getHZInstance();
        try {
            Map<Integer, String> map = hazelcastInstance.getReplicatedMap(COLLECTION_NAME);
            String cacheStr = map.remove(id);
            if (cacheStr == null) {
                return null;
            } else {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(cacheStr, TestPojo.class);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error remove", e);
        }
    }

    public void clear() {
        HazelcastInstance hazelcastInstance = getHZInstance();
        try {
            Map<Integer, String> map = hazelcastInstance.getReplicatedMap(COLLECTION_NAME);
            map.clear();
        } catch (Exception e) {
            throw new RuntimeException("Error clear", e);
        }
    }

}
