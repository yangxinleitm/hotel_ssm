package cn.hotel.serviceApi.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisTest {
    @Test
    public void testJedis(){
        Jedis jedis = new Jedis("192.168.25.8", 6379);
        String key1 = jedis.get("customer");
//        JSONObject json = JSON.parseObject(key1, JSONObject.class);
//        JSONObject getUserPostByIdResult = (JSONObject) json.get("GetUserPostByIdResult");
//        String id = getUserPostByIdResult.get("ID").toString();
        System.out.println(key1);
    }
}
