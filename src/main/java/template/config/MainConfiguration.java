package template.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class MainConfiguration {

    @Value("${template.cron}")
    private String templateCron;

    @Bean
    MainConfiguration getConfiguration() {
        return new MainConfiguration();
    }
}
