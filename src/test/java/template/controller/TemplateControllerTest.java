package template.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TemplateControllerTest {

    // URLS
    private final String LOAD_URL = "/api/template/load";

    @Autowired
    MockMvc mvc;

    @Test
    void addTemplate() throws Exception {
        mvc.perform(post(LOAD_URL)
                .contentType("application/json")
                .content("{\n" +
                        "\"templateId\": \"internshipRequest\",\n" +
                        "\"template\": \"Jetbrains Internship in $teamName$ team and my name is $name$\",\n" +
                        "\"recipients\": [\"http://localhost:8080/endpoint/first\", \"http://localhost:8080/endpoint/second\"]\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
