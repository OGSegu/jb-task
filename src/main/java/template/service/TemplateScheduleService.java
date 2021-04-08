package template.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Data
@AllArgsConstructor
@Service
public class TemplateScheduleService {

    private RestTemplate restTemplate;
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private final CronTrigger cronTrigger = new CronTrigger("* 10 * * * ?");

    public void execute(String message, List<String> endpoints) {
        endpoints.forEach(endpoint -> execute(message, endpoint));
    }

    public void execute(String message, String endpoint) {
        threadPoolTaskScheduler.schedule(() -> restTemplate.postForObject(endpoint, message, String.class), cronTrigger);
    }

}
