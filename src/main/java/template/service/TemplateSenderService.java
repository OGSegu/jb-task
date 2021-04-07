package template.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import template.dto.TemplateSenderDTO;
import template.entity.Template;
import template.exception.TemplateNotFoundException;
import template.repository.TemplateRepository;

import java.util.Map;


@Slf4j
@Data
@AllArgsConstructor
@EnableScheduling
@Service
public class TemplateSenderService {

    private RestTemplate restTemplate;

    private TemplateParser templateMsgParser;
    private TemplateRepository templateRepo;

    public String save(TemplateSenderDTO templateSender) throws TemplateNotFoundException {
        String id = templateSender.getTemplateId();
        Template template = getTemplateEntityById(id);
        addVariables(template.getVariables(), templateSender.getVariables());
        templateRepo.save(template);
        return getTemplateMsg(template);
    }

    private void addVariables(Map<String, String> entityVariableMap, Map.Entry<String, String>[] variables) {
        for (Map.Entry<String, String> variable : variables) {
            entityVariableMap.put(variable.getKey(), variable.getValue());
        }
    }

    private String getTemplateMsg(String templateId) throws TemplateNotFoundException {
        Template templateEntity = getTemplateEntityById(templateId);
        return getTemplateMsg(templateEntity);
    }

    private String getTemplateMsg(Template templateEntity) {
        Map<String, String> variables = templateEntity.getVariables();
        return templateMsgParser.replaceVarsAndGetMsg(templateEntity.getTemplateMsg(), variables);
    }

    private Template getTemplateEntityById(String id) throws TemplateNotFoundException {
        return templateRepo.findById(id).orElseThrow(TemplateNotFoundException::new);
    }

    @ExceptionHandler(TemplateNotFoundException.class)
    public String templateError() {
        return "Error";
    }
}
