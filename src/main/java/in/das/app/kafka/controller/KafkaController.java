package in.das.app.kafka.controller;

import in.das.app.kafka.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaService kafkaService;

    @GetMapping("/test")
    public String test(){
        return "server up & running.";
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publishToKafka(@RequestBody Object request){
        String message = kafkaService.writeMessageToKafka(request);
        return ResponseEntity.ok(Map.of("message","Message Published", "data", message));
    }
}
