package template.service;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import template.config.MainConfiguration;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Data
@Service
public class TemplateScheduleService {

    @NonNull
    private MainConfiguration mainConfiguration;
    @NonNull
    private RestTemplate restTemplate;
    @NonNull
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private CronTrigger cronTrigger;

    @PostConstruct
    void init() {
        cronTrigger = new CronTrigger(mainConfiguration.getTemplateCron());
    }

    public void execute(String message, List<String> endpoints) {
        endpoints.forEach(endpoint -> execute(message, endpoint));
        log.info("Message in execute: " + message);
    }

    public void execute(String message, String endpoint) {
        threadPoolTaskScheduler.schedule(() -> restTemplate.postForObject(endpoint, message, String.class), cronTrigger);
    }

}
