package com.ftb.lqp.kafkaclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Service
public class KafkaProducerConsumer 
{
	private final Logger logger 
		= LoggerFactory.getLogger(KafkaProducerConsumer.class);
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = AppConstants.GROUP_ID)
	public void consume(String message) {
		logger.info(String.format("Message recieved -> %s", message));
		redisTemplate.opsForList().leftPush("lqp-messages", message);
	}
	
	public void sendMessage(String message) 
	{
		logger.info(String.format("Message sent -> %s", message));
		this.kafkaTemplate.send(AppConstants.TOPIC_NAME, message);
	}


}
