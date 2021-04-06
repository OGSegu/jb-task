package template.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import template.dto.TemplateLoaderDTO;
import template.dto.TemplateSenderDTO;
import template.entity.TemplateEntity;
import template.mapper.TemplateMapper;
import template.service.TemplateLoaderService;
import template.service.TemplateSenderService;

@Slf4j
@Data
@AllArgsConstructor
@Controller
@RequestMapping("/api/template")
public class TemplateController {

    private TemplateLoaderService templateLoaderService;
    private TemplateSenderService templateSenderService;

    @PostMapping("/load")
    public void load(@RequestBody TemplateLoaderDTO template) {
        TemplateEntity templateEntity = TemplateMapper.INSTANCE.toEntity(template);
        templateLoaderService.save(templateEntity);
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody TemplateSenderDTO templateSender) {
        templateSenderService.save(templateSender);
    }

}
