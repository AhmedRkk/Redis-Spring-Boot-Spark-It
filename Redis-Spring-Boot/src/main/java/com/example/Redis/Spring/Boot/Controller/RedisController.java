package com.example.Redis.Spring.Boot.Controller;

import com.example.Redis.Spring.Boot.Entity.User;
import com.example.Redis.Spring.Boot.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
public class RedisController {

    @Autowired
    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping("/Cache")
    public ResponseEntity<String> save(@RequestParam String key, @RequestParam String value) {
        redisService.save(key, value);
        return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
    }
    @GetMapping("/Cache/{key}")
    public Object get(@PathVariable String key) {
        return redisService.get(key);
    }

    @PostMapping("/CacheList")
    public Object saveList(@RequestParam String key, @RequestParam Object value) {
        redisService.saveList(key, value);
        return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
    }
    @GetMapping("/CacheList/{key}")
    public Object getList(@PathVariable String key) {
        return redisService.getList(key);
    }
    @PostMapping("/CacheSet")
    public Object saveSet(@RequestParam String key, @RequestParam Object value) {
        redisService.saveSet(key, value);
        return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
    }
    @GetMapping("/CacheSet/{key}")
    public Object getSet(@PathVariable String key) {
        return redisService.getSet(key);
    }
    @PostMapping("/CacheJson")
    public ResponseEntity<String> saveJson(@RequestParam String key, @RequestBody User value) {
        redisService.saveJson(key, value);
        return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
    }
    @GetMapping("/CacheJson/{key}")
    public User getUser(@PathVariable String key) {
        return redisService.getJson(key);
    }
    @DeleteMapping("/CacheJson/{key}")
    public void deleteUser(@PathVariable String key) {
         redisService.deleteJson(key);
    }

    @PostMapping("/HCache")
    public ResponseEntity<String> saveHashCache(@RequestParam String key, @RequestParam String value) throws NoSuchAlgorithmException {
        redisService.saveHash(key, value);
        return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
    }


    @GetMapping("/HCache/{key}")
    public Object getHCache(@PathVariable String key) throws NoSuchAlgorithmException {
        return redisService.Hget(key);
    }
    @DeleteMapping("/delete/{key}")
    public ResponseEntity<String> delete(@PathVariable String key) {
        redisService.delete(key);
        return new ResponseEntity<>("Data deleted successfully", HttpStatus.OK);
    }


}
