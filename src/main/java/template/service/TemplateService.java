package template.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import template.entity.TemplateEntity;
import template.repository.TemplateRecipientsRepository;
import template.repository.TemplateRepository;

@Data
@AllArgsConstructor
@Service
public class TemplateService {

    private TemplateRecipientsRepository templateRecipientsRepository;
    private TemplateRepository templateRepository;

    public void save(TemplateEntity templateEntity) {
        templateRepository.save(templateEntity);
    }

}
