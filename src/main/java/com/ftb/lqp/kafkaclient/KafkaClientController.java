package com.ftb.lqp.kafkaclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaClientController {

	@Autowired
	private final KafkaProducerConsumer kafkaService;
	
	@Autowired
	private RedisTemplate redisTemplate;

	public KafkaClientController(KafkaProducerConsumer kafkaService) {
		this.kafkaService = kafkaService;
	}

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		this.kafkaService.sendMessage(message);
	}
	
	@GetMapping(value = "/list")
	public List<String> listMessages() {
		
		List<String> messages = new ArrayList();
		int len = redisTemplate.opsForHash().size("lqp-messages");
		for (int i=0; i < len; i++ ) {
			messages.add(redisTemplate.opsForList().leftPop("lqp-messages"));			
		}
		
		return messages;
	}

}