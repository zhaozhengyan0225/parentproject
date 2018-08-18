package cn.jiang;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestRedis {
	@Autowired
	private Jedis jedis;


    @Autowired
    private JedisPool jedisPool;
	
	@Test
	public void testSpringJedis() throws Exception {
		jedis.set("oo", "aaa");
	}
	
	
	@Test
	public void testRedis() throws Exception {
		Jedis jedis = new Jedis("192.168.200.150",6379);
		Long pno = jedis.incr("pno");
		System.out.println(pno);
		
		jedis.close();
	}
	@Test
	public void testLocalHostJedis() throws Exception {
		//连接本地的 Redis 服务
		Jedis jedis = new Jedis("127.0.0.1",6379);
		System.out.println("Connection to server sucessfully");
		//查看服务是否运行
		System.out.println("Server is running: "+jedis.ping());
		
		Set<String> keys = jedis.keys("*");
		Iterator<String> iterator = keys.iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			System.out.println("key--------->"+key);
			String string = jedis.get(key);
			System.out.println("value--------->"+string);
		}
		jedis.close();
	}
	@Test
	public void testRedisPool() throws Exception {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.200.150", 6379, 60, "654321");
        Jedis jedis = jedisPool.getResource();
        jedis.set("pno", "1000");

        System.out.println(jedis.get("pno"));

		jedis.close();
	}

    @Test
    public void testRedisPoolAutowired() throws Exception {
        Jedis jedis = jedisPool.getResource();
//        jedis.set("pno", "1000");

        System.out.println(jedis.get("pno"));

        jedis.close();
    }
}
