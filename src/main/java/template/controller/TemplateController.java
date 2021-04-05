package template.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import template.dto.TemplateDTO;
import template.entity.TemplateEntity;
import template.mapper.TemplateMapper;
import template.service.TemplateService;

@Slf4j
@Data
@AllArgsConstructor
@Controller
@RequestMapping("/api/template")
public class TemplateController {

    private TemplateService templateService;

    @PostMapping("/load")
    public void load(@RequestBody TemplateDTO template) {
        TemplateEntity templateEntity = TemplateMapper.INSTANCE.toEntity(template);
        templateService.save(templateEntity);
    }

}
