package in.das.app.kafka.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AppUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Optional<String> getValueAsString(Object object){
        String strValue;
        try {
            strValue = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(strValue);
    }
}
