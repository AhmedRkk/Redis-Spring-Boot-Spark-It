package com.example.Redis.Spring.Boot.Service;

import com.example.Redis.Spring.Boot.Classe.RedisJsonClient;
import com.example.Redis.Spring.Boot.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class RedisService {
    @Autowired
    private  RedisTemplate<String, Object> redisTemplate;




    private RedisJsonClient redisJsonClient =new RedisJsonClient("localhost",12000);


    public String hash( String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
    public void saveList(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }
    public Object getList(String key) {
        return  redisTemplate.opsForList().range(key, 0, -1);
    }
    public void saveSet(String key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }
    public Object getSet(String key) {
        return  redisTemplate.opsForSet().members(key);
    }


    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }
    public Object get(String key) {
        return   redisTemplate.opsForValue().get(key);
    }

    public void saveJson(String key, User value)  {
        redisJsonClient.setValue(key,value);
    }

    public User getJson(String key) {
        redisJsonClient.deleteValue("user");
        return   redisJsonClient.getValue(key);
    }
    public void deleteJson(String key) {
        redisJsonClient.deleteValue("user");

    }
    public void saveHash(String key, String value) throws NoSuchAlgorithmException {
        redisTemplate.opsForHash().put(key,hash(key),hash(value));
    }

    public Object Hget(String key) throws NoSuchAlgorithmException {
        return redisTemplate.opsForHash().get(key,hash(key));
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}