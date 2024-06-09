package in.das.app.kafka.kafka;

import in.das.app.kafka.values.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishMessage(String message){
        log.info("publishing message:'{}', to topic:'{}'", message,AppConstants.TOPIC_NAME);
        kafkaTemplate.send(AppConstants.TOPIC_NAME,message);
    }
}
