package template.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;
import template.entity.Template;
import template.entity.Variable;
import template.exception.VariableTypeNotFoundException;
import template.repository.TemplateRepository;
import template.repository.VariableRepository;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TemplateControllerTest {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private VariableRepository variableRepository;

    // URLS
    private static final String LOAD_TEMPLATE_URL = "http://localhost:8080/api/template/load";
    private static final String SEND_WITH_VARIABLES_URL = "http://localhost:8080/api/template/send";
    private static final String ENDPOINT_FIRST = "http://localhost:8080/endpoint/first";
    private static final String ENDPOINT_SECOND = "http://localhost:8080/endpoint/second";

    // Strings
    private static final String WRONG_TEMPLATE_ID = "wrongTemplateId";
    private static final String TEMPLATE_ID = "internshipRequest";
    private static final String TEMPLATE_MSG = "Jetbrains Internship in $teamName$ team in $year$ year";
    private static final String CONTENT_TYPE = "application/json";

    // Variables
    private static final String TEAM_NAME = "teamName";
    private static final String YEAR = "year";
    private static final String TEAM_NAME_VALUE = "Analytics Platform";
    private static final int YEAR_VALUE = 2021;


    @Autowired
    MockMvc mvc;

    @BeforeEach
    public void before() {
        variableRepository.deleteAll();
        variableRepository.flush();
        templateRepository.deleteAll();
        templateRepository.flush();
    }


    @Test
    void createTemplateWithoutVariables() throws Exception {
        String content = String.format("{\n" +
                "\"templateId\": \"%s\",\n" +
                "\"template\": \"%s\",\n" +
                "\"recipients\": [\"%s\", \"%s\"]\n" +
                "}", TEMPLATE_ID, TEMPLATE_MSG, ENDPOINT_FIRST, ENDPOINT_SECOND);
        mvc.perform(post(LOAD_TEMPLATE_URL)
                .contentType(CONTENT_TYPE)
                .content(content))
                .andExpect(status().isOk());
        Optional<Template> template = templateRepository.findById(TEMPLATE_ID);
        Assert.isTrue(template.isPresent(), "Template should be saved");
        Assert.hasText(template.get().getTemplateMsg(), TEMPLATE_MSG);
        Assert.hasText(template.get().getRecipients().get(0), ENDPOINT_FIRST);
        Assert.hasText(template.get().getRecipients().get(1), ENDPOINT_SECOND);
    }

    @Test
    void createTemplateWithVariablesType() throws Exception {
        String content = String.format("{\n" +
                "\"templateId\": \"%s\",\n" +
                "\"template\": \"%s\",\n" +
                "\"recipients\": [\"%s\", \"%s\"],\n" +
                "\"variablesType\": [{\"%s\": \"string\"}, {\"%s\": \"int\"}]\n" +
                "}", TEMPLATE_ID, TEMPLATE_MSG, ENDPOINT_FIRST, ENDPOINT_SECOND, TEAM_NAME, YEAR);
        mvc.perform(post(LOAD_TEMPLATE_URL)
                .contentType(CONTENT_TYPE)
                .content(content))
                .andExpect(status().isOk());
        Optional<Template> template = templateRepository.findById(TEMPLATE_ID);
        Assert.isTrue(template.isPresent(), "Template should be saved");
        Assert.hasText(template.get().getTemplateMsg(), TEMPLATE_MSG);
        Assert.hasText(template.get().getRecipients().get(0), ENDPOINT_FIRST);
        Assert.hasText(template.get().getRecipients().get(1), ENDPOINT_SECOND);
        Variable firstVariable = variableRepository.findByTemplateIdAndName(template.get().getTemplateId(), TEAM_NAME);
        Variable secondVariable = variableRepository.findByTemplateIdAndName(template.get().getTemplateId(), YEAR);
        Assert.notNull(firstVariable, "Variable should not be a null");
        Assert.notNull(secondVariable, "Variable should not be a null");
        String emptyString = "";
        Integer zeroInteger = 0;
        Assert.isTrue(firstVariable.getType().isInstance(emptyString), "First variable should be a String");
        Assert.isTrue(secondVariable.getType().isInstance(zeroInteger), "Second variable should be an Integer");
    }

    @Test
    void createTemplateWithWrongVariableType() throws Exception {
        String content = String.format("{\n" +
                "\"templateId\": \"%s\",\n" +
                "\"template\": \"%s\",\n" +
                "\"recipients\": [\"%s\", \"%s\"],\n" +
                "\"variablesType\": [{\"%s\": \"jetbrains\"}, {\"%s\": \"int\"}]\n" +
                "}", TEMPLATE_ID, TEMPLATE_MSG, ENDPOINT_FIRST, ENDPOINT_SECOND, TEAM_NAME, YEAR);
        mvc.perform(post(LOAD_TEMPLATE_URL)
                .contentType(CONTENT_TYPE)
                .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(VariableTypeNotFoundException.MSG));
    }
}
