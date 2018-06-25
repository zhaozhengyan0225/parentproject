package cn.jiang;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestRedis {
	@Autowired
	private Jedis jedis;
	
	@Test
	public void testSpringJedis() throws Exception {
		jedis.set("oo", "aaa");
	}
	
	
	@Test
	public void testRedis() throws Exception {
		Jedis jedis = new Jedis("192.168.200.128",6379);
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
	}
}
