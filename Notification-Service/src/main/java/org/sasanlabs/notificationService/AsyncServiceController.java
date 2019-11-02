package org.sasanlabs.notificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncServiceController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@GetMapping("/")
	public String allEndPoints() {
		kafkaTemplate.send("test2", "SASAN");
		return "sasan";
	}

	
	//$KAFKA_HOME/bin/kafka-topics.sh --create --topic test1 --partitions 4 --replication-factor 1 --zookeeper zookeeper:2181
	//$KAFKA_HOME/bin/kafka-console-producer.sh --topic=test1 --broker-list=`broker-list.sh`
	//$KAFKA_HOME/bin/kafka-console-consumer.sh --topic=test1 --bootstrap-server localhost:9092
	@KafkaListener(topics = "test2", groupId = "group_id")
	public void consume(String message){
		System.out.println(String.format("$$ -> Consumed Message -> %s",message));
	}
}
