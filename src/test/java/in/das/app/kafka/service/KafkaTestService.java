package in.das.app.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
public class KafkaTestService {

    public List<String> getMessages() {
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(getProperties());

        // Explicitly assign the partition
        TopicPartition partition = new TopicPartition("demo-topic1", 0);
        //kafkaConsumer.assign(Collections.singleton(partition));

        kafkaConsumer.subscribe(Collections.singleton("demo-topic1"));

        // Seek to the desired offset
        //kafkaConsumer.seek(partition, 10);

        List<String> messages = new ArrayList<>();

        try {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(5000));
            for (ConsumerRecord<String, String> record : consumerRecords) {
                messages.add(record.value());
                System.out.println("partition: " + record.partition() + "; topic: " + record.topic() + "; value: " + record.value() + "; offset: " + record.offset());
            }
        } finally {
            kafkaConsumer.close();
        }

        return messages;
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group-1");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }
}
