package template.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import template.dto.TemplateSenderDTO;
import template.entity.TemplateEntity;
import template.entity.TemplateVariable;
import template.exception.TemplateNotFoundException;
import template.repository.TemplateRepository;

import java.util.List;
import java.util.Map;


@Slf4j
@Data
@AllArgsConstructor
@EnableScheduling
@Service
public class TemplateSenderService {

    private TemplateRepository templateRepo;

    public void save(TemplateSenderDTO templateSender) {

        String id = templateSender.getTemplateId();
        TemplateEntity templateEntity = getTemplateEntityById(id);

        addVariables(id, templateEntity.getVariables(), templateSender.getVariables());

        templateRepo.save(templateEntity);
    }

    private TemplateEntity getTemplateEntityById(String id) {
        return templateRepo.findById(id).orElseThrow(TemplateNotFoundException::new);
    }

    private void addVariables(String id, List<TemplateVariable> entitySet, Map.Entry<String, String>[] variables) {
        for (Map.Entry<String, String> variable : variables) {
            entitySet.add(new TemplateVariable(id, variable.getKey(), variable.getValue()));
        }
    }


}
