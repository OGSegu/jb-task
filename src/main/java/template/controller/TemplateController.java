package template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import template.dto.TemplateDTO;

@Controller
@RequestMapping("/api/template")
public class TemplateController {

    @PostMapping("/load")
    public void load(@RequestBody TemplateDTO template) {

    }

}
