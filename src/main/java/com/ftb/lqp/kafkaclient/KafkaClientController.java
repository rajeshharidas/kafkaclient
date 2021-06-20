package com.ftb.lqp.kafkaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaClientController {

	@Autowired
	private final KafkaProducerConsumer kafkaService;

	public KafkaClientController(KafkaProducerConsumer kafkaService) {
		this.kafkaService = kafkaService;
	}

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		this.kafkaService.sendMessage(message);
	}
	

}