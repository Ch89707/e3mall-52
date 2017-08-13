package cn.e3mall.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.jedis.JedisClientCluster;

public class JedisClientTest {


	@Test
	public void t1(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClient = context.getBean(JedisClient.class);
		String string = jedisClient.get("mytest111");
		System.out.println(string);
	}

}
