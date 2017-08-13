package cn.e3mall.test;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JInternalFrame.JDesktopIcon;

import org.junit.Test;

import redis.clients.jedis.Client;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest1 {

	
	@Test
	public void t1(){
		
		Jedis jedis = new Jedis("192.168.25.128",7001);
	
		 jedis.set("hello", "word");
		String string2 = jedis.get("hello");
		System.out.println(string2);
		
		jedis.close();
	}
	
	@Test
	public void testJedisPool(){
		
		JedisPool jedisPool = new JedisPool("192.168.25.128",6379);
		Jedis jedis = jedisPool.getResource();
		jedis.set("h1", "value");
		String string = jedis.get("h1");
		System.out.println(string);
		jedis.close();
		jedisPool.close();
	}
	
	@Test
	public void testJedisClusr(){
		Set<HostAndPort> nodes=new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.128", 7001));
		nodes.add(new HostAndPort("192.168.25.128", 7002));
		nodes.add(new HostAndPort("192.168.25.128", 7003));
		nodes.add(new HostAndPort("192.168.25.128", 7004));
		nodes.add(new HostAndPort("192.168.25.128", 7005));
		nodes.add(new HostAndPort("192.168.25.128", 7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		for (int i=1;i<20;i++) {
		Long del = jedisCluster.del("mytest"+i);
		System.out.println(del);
		}
		jedisCluster.close();
	}
	
}
