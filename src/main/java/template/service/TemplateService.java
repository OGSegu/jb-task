package template.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import template.dto.TemplateDTO;
import template.entity.Template;
import template.entity.Variable;
import template.mapper.TemplateMapper;
import template.model.ClassType;
import template.repository.TemplateRepository;
import template.repository.VariableRepository;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Slf4j
@Data
@AllArgsConstructor
@Service
public class TemplateService {

    private TemplateParser templateParser;
    private TemplateRepository templateRepository;
    private VariableRepository variableRepository;

    public void save(TemplateDTO templateDTO) {
        Template templateEntity = TemplateMapper.INSTANCE.toEntity(templateDTO);
        parseVariablesType(templateEntity, templateDTO);
        templateRepository.save(templateEntity);
    }

    public void parseVariablesType(Template templateEntity, TemplateDTO templateDTO) {
        Set<Variable> entityVariables = templateEntity.getVariables();
        Map.Entry<String, String>[] variablesType = templateDTO.getVariablesType();
        if (variablesType == null) {
            return;
        }
        Arrays.stream(variablesType).forEach((Map.Entry<String, String> entry) -> {
            Variable variable = null;
            try {
                variable = new Variable(templateEntity.getTemplateId(),
                        entry.getKey(),
                        Class.forName(ClassType.valueOf(entry.getValue().toUpperCase()).getPath()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            entityVariables.add(variable);
        });

    }


}
