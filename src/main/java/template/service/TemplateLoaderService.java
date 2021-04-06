package template.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import template.entity.TemplateEntity;
import template.repository.TemplateRecipientsRepository;
import template.repository.TemplateRepository;

@Slf4j
@Data
@AllArgsConstructor
@Service
public class TemplateLoaderService {

    private TemplateRecipientsRepository templateRecipientsRepository;
    private TemplateRepository templateRepository;

    public void save(TemplateEntity templateEntity) {
        templateRepository.save(templateEntity);
    }

}
