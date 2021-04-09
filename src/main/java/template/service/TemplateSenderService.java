package template.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import template.dto.TemplateSenderDTO;
import template.entity.Template;
import template.entity.Variable;
import template.exception.TemplateNotFoundException;
import template.repository.TemplateRepository;
import template.repository.VariableRepository;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Data
@AllArgsConstructor
@EnableScheduling
@Service
public class TemplateSenderService {

    private TemplateScheduleService templateScheduleService;

    private TemplateParser templateMsgParser;
    private VariableRepository variableRepo;
    private TemplateRepository templateRepo;

    public void save(TemplateSenderDTO templateSender) throws TemplateNotFoundException {
        String id = templateSender.getTemplateId();
        Template template = getTemplateEntityById(id);
        addVariables(template, templateSender.getVariables());
        String message = replaceVariablesInMsg(template);
        addMsgToHistory(template, message);
        templateRepo.save(template);
        templateScheduleService.execute(message, template.getRecipients());
    }

    private <V> void addVariables(Template template, Map.Entry<String, ?>[] variables) {
        for (Map.Entry<String, ?> receivedVariable : variables) {
            String receivedVariableName = receivedVariable.getKey();
            V receivedVariableValue = (V) receivedVariable.getValue();
            Variable repoVariable = variableRepo.findByTemplateIdAndName(template.getTemplateId(), receivedVariableName);
            if (repoVariable == null) {
                repoVariable = new Variable(template.getTemplateId(), receivedVariableName, String.valueOf(receivedVariableValue));
            } else {
                Class<?> classType = repoVariable.getType();
                if (classType.isInstance(receivedVariableValue)) {
                    repoVariable.setValue(String.valueOf(receivedVariableValue));
                } else {
                    continue;
                }
            }
            variableRepo.save(repoVariable);
        }
    }

    private String replaceVariablesInMsg(Template templateEntity) {
        Map<String, String> variables = templateEntity.getVariables().stream()
                .collect(Collectors.toMap(Variable::getName, Variable::getValue));
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
