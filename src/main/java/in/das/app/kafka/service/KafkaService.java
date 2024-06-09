package in.das.app.kafka.service;

import in.das.app.kafka.kafka.KafkaMessagePublisher;
import in.das.app.kafka.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaService {

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    public String writeMessageToKafka(Object object){
        String message = AppUtils.getValueAsString(object).orElse("");
        kafkaMessagePublisher.publishMessage(message);
        return message;
    }


}
