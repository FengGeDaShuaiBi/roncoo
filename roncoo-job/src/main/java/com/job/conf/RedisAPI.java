package com.job.conf;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisAPI {

    public JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    //get
    public String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            returnResource(jedisPool, jedis);
        }

        return value;
    }

    public static void returnResource(JedisPool pool, Jedis redis) {
        if (redis != null) {
            pool.returnResource(redis);
        }
    }

    //set
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(key, value);
        jedisPool.returnResource(jedis);
        return result;
    }

    public String set(String key, int expire, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.setex(key, expire, value);
        jedisPool.returnResource(jedis);
        return result;
    }

    //exists
    public boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.exists(key);
        jedisPool.returnResource(jedis);
        return result;
    }

    //ttl
    public long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedisPool.returnResource(jedis);
        return result;
    }

    //del
    public long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedisPool.returnResource(jedis);
        return result;
    }
}
