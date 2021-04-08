package template.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    private TemplateScheduleService templateScheduleService;

    private TemplateParser templateMsgParser;
    private TemplateRepository templateRepo;

    public void save(TemplateSenderDTO templateSender) throws TemplateNotFoundException {
        String id = templateSender.getTemplateId();
        Template template = getTemplateEntityById(id);
        addVariables(template.getVariables(), templateSender.getVariables());
        String message = replaceVariablesInMsg(template);
        addMsgToHistory(template, message);
        templateRepo.save(template);
        templateScheduleService.execute(message, template.getRecipients());
    }

    private void addVariables(Map<String, String> entityVariableMap, Map.Entry<String, String>[] variables) {
        for (Map.Entry<String, String> variable : variables) {
            entityVariableMap.put(variable.getKey(), variable.getValue());
        }
    }

    private String replaceVariablesInMsg(Template templateEntity) {
        Map<String, String> variables = templateEntity.getVariables();
        return templateMsgParser.replaceVarsAndGetMsg(templateEntity.getTemplateMsg(), variables);
    }

    public void addMsgToHistory(Template template, String message) {
        template.getSentMessagesHistory().add(message);
    }

    private Template getTemplateEntityById(String id) throws TemplateNotFoundException {
        return templateRepo.findById(id).orElseThrow(TemplateNotFoundException::new);
    }

    @ExceptionHandler(TemplateNotFoundException.class)
    public String templateError() {
        return "Error";
    }
}
