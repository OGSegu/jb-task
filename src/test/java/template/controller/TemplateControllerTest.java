package template.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;
import template.entity.Template;
import template.exception.TemplateNotFoundException;
import template.repository.TemplateRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TemplateControllerTest {

    // Repo
    @Autowired
    private TemplateRepository templateRepository;


    // URLS
    private final String LOAD_URL = "/api/template/load";

    private final String TEMPLATE_ID = "internshipRequest";
    private final String TEMPLATE_MSG = "Jetbrains Internship in $teamName$ team";
    private final String ENDPOINT_FIRST = "http://localhost:8080/endpoint/first";
    private final String ENDPOINT_SECOND = "http://localhost:8080/endpoint/second";


    @Autowired
    MockMvc mvc;

    @Test
    void addTemplate() throws Exception {
        mvc.perform(post(LOAD_URL)
                .contentType("application/json")
                .content(String.format("{\n" +
                        "\"templateId\": \"%s\",\n" +
                        "\"template\": \"%s\",\n" +
                        "\"recipients\": [\"%s\", \"%s\"]\n" +
                        "}", TEMPLATE_ID, TEMPLATE_MSG, ENDPOINT_FIRST, ENDPOINT_SECOND)))
                .andDo(print())
                .andExpect(status().isOk());
        Template template;
        Assert.isInstanceOf(Template.class, template = templateRepository.findById(TEMPLATE_ID).orElseThrow(TemplateNotFoundException::new));
        Assert.hasText(TEMPLATE_MSG, template.getTemplateMsg());
    }

}
