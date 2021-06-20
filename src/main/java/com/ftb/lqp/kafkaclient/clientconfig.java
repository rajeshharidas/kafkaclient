package com.ftb.lqp.kafkaclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class clientconfig {

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		 RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		    redisTemplate.setKeySerializer(new StringRedisSerializer());
		    redisTemplate.setValueSerializer(new GenericToStringSerializer<>(String.class));
		    // avoids proxy
		    redisTemplate.setExposeConnection(true);
		    redisTemplate.setConnectionFactory(factory);
		    redisTemplate.afterPropertiesSet();
		    return redisTemplate;
	}

}
