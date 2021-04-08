package template.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import template.entity.Template;
import template.repository.TemplateRepository;

@Slf4j
@Data
@AllArgsConstructor
@Service
public class TemplateService {
    private TemplateParser templateParser;
    private TemplateRepository templateRepository;

    public void save(Template template) {
        templateRepository.save(template);
    }

}
