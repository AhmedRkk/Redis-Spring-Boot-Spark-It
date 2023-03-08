package com.example.Redis.Spring.Boot.Classe;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

public class RedisJsonClient {
    private final RedissonClient redissonClient;
    public RedisJsonClient(String host, int port) {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setAddress("redis://Spark-It2020@" + host + ":" + port);
        redissonClient = Redisson.create(config);
    }

    public <T> void setValue(String key, T value) {
        RMap<String, T> map = redissonClient.getMap(key, new JsonJacksonCodec());
        map.put(key, value);

    }
    public <T> T getValue(String key) {
        RMap<String, T> map = redissonClient.getMap(key, new JsonJacksonCodec());
        return map.get(key);
    }
    public void deleteValue(String key) {
        RMap<String, Object> map = redissonClient.getMap(key, new JsonJacksonCodec());
        map.remove(key);
    }
}


