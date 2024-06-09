package in.das.app.kafka.kafka;

import in.das.app.kafka.values.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
public class KafkaMessageConsumer {

//    @KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = AppConstants.GROUP_ID)
//    public void consumeKafkaMessages(String consumedMessage){
//        log.info("Received Message: {}", consumedMessage);
//    }
}
