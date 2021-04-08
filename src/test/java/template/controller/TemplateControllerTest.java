package template.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Assert;
import template.entity.Template;
import template.exception.TemplateNotFoundException;
import template.repository.TemplateRepository;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TemplateControllerTest {

    @Autowired
    private TemplateRepository templateRepository;

    // URLS
    private static final String LOAD_TEMPLATE_URL = "http://localhost:8080/api/template/load";
    private static final String SEND_WITH_VARIABLES_URL = "http://localhost:8080/api/template/send";
    private static final String ENDPOINT_FIRST = "http://localhost:8080/endpoint/first";
    private static final String ENDPOINT_SECOND = "http://localhost:8080/endpoint/second";

    // Strings
    private static final String WRONG_TEMPLATE_ID = "wrongTemplateId";
    private static final String TEMPLATE_ID = "internshipRequest";
    private static final String TEMPLATE_MSG = "Jetbrains Internship in $teamName$ team";
    private static final String CONTENT_TYPE = "application/json";

    // Variables
    private static final String TEAM_NAME = "Analytics Platform";
    private static final int YEAR = 2021;


    @Autowired
    MockMvc mvc;


    private ResultActions addTemplate() throws Exception {
        return mvc.perform(post(LOAD_TEMPLATE_URL)
                .contentType(CONTENT_TYPE)
                .content(String.format("{\n" +
                        "\"templateId\": \"%s\",\n" +
                        "\"template\": \"%s\",\n" +
                        "\"recipients\": [\"%s\", \"%s\"]\n" +
                        "}", TEMPLATE_ID, TEMPLATE_MSG, ENDPOINT_FIRST, ENDPOINT_SECOND)));
    }

    private ResultActions addVariables() throws Exception {
        return mvc.perform(post(SEND_WITH_VARIABLES_URL)
                .contentType(CONTENT_TYPE)
                .content(String.format("{\n" +
                        "\"templateId\": \"%s\",\n" +
                        "\"variables\": [{\"teamName\": \"%s\"}, {\"year\": \"%d\"}]\n" +
                        "}", TEMPLATE_ID, TEAM_NAME, YEAR)));
    }

    @Test
    void addTemplateSuccess() throws Exception {
        ResultActions resultActions = addTemplate();
        resultActions
                .andDo(print())
                .andExpect(status().isOk());
        Template template;
        Assert.isInstanceOf(Template.class, template = templateRepository.findById(TEMPLATE_ID).orElseThrow(TemplateNotFoundException::new));
        Assert.hasText(TEMPLATE_MSG, template.getTemplateMsg());
        List<String> endpoints = template.getRecipients();
        Assert.isTrue(endpoints.get(0).equals(ENDPOINT_FIRST), "First endpoint should be received");
        Assert.isTrue(endpoints.get(1).equals(ENDPOINT_SECOND), "Second endpoint should be received");
    }

    @Test
    void templateNotExists() {
        assertThrows(TemplateNotFoundException.class, () ->
                templateRepository.findById(WRONG_TEMPLATE_ID).orElseThrow(TemplateNotFoundException::new)
        );
    }

    @Test
    void addVariablesSuccess() throws Exception {
        addTemplate();
        addVariables()
                .andDo(print())
                .andExpect(status().isOk());
        Template template;
        Assert.isInstanceOf(Template.class, template = templateRepository.findById(TEMPLATE_ID).orElseThrow(TemplateNotFoundException::new));
        Map<String, String> variables = template.getVariables();
        Assert.hasText(TEAM_NAME, variables.get("teamName"));
        Assert.hasText(String.valueOf(YEAR), variables.get("year"));
    }
}
