package template.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import template.dto.TemplateSenderDTO;
import template.entity.Template;
import template.exception.TemplateNotFoundException;
import template.repository.TemplateRepository;

import java.util.List;
import java.util.Map;


@Slf4j
@Data
@AllArgsConstructor
@Service
public class TemplateSenderService {

    private RestTemplate restTemplate;

    private TemplateParser templateMsgParser;
    private TemplateRepository templateRepo;

    public void save(TemplateSenderDTO templateSender) throws TemplateNotFoundException {
        String id = templateSender.getTemplateId();
        Template template = getTemplateEntityById(id);
        addVariables(template.getVariables(), templateSender.getVariables());
        templateRepo.save(template);
        sendTemplate(template);
    }

    private void addVariables(Map<String, String> entityVariableMap, Map.Entry<String, String>[] variables) {
        for (Map.Entry<String, String> variable : variables) {
            entityVariableMap.put(variable.getKey(), variable.getValue());
        }
    }

    private String getTemplateMsg(Template templateEntity) {
        Map<String, String> variables = templateEntity.getVariables();
        return templateMsgParser.replaceVarsAndGetMsg(templateEntity.getTemplateMsg(), variables);
    }

    private void sendTemplate(Template template) {
        String templateMsg = getTemplateMsg(template);
        List<String> endpointList = template.getRecipients();
        endpointList.forEach(endpoint -> restTemplate.postForObject(endpoint, templateMsg, String.class));
    }

    private Template getTemplateEntityById(String id) throws TemplateNotFoundException {
        return templateRepo.findById(id).orElseThrow(TemplateNotFoundException::new);
    }

    @ExceptionHandler(TemplateNotFoundException.class)
    public String templateError() {
        return "Error";
    }
}
