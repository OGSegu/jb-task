package template.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import template.dto.TemplateDTO;
import template.dto.TemplateSenderDTO;
import template.entity.Template;
import template.exception.TemplateNotFoundException;
import template.mapper.TemplateMapper;
import template.service.TemplateService;
import template.service.TemplateSenderService;

@Slf4j
@Data
@AllArgsConstructor
@Controller
@RequestMapping("/api/template")
public class TemplateController {

    private TemplateService templateService;
    private TemplateSenderService templateSenderService;

    @PostMapping("/load")
    public ResponseEntity<String> load(@RequestBody TemplateDTO template) {
        Template templateEntity = TemplateMapper.INSTANCE.toEntity(template);
        templateService.save(templateEntity);
        return new ResponseEntity<>("Received", HttpStatus.OK);
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody TemplateSenderDTO templateSender) throws TemplateNotFoundException {
        templateSenderService.save(templateSender);
    }

    @ExceptionHandler(TemplateNotFoundException.class)
    public ResponseEntity<String> templateNotFound() {
        return new ResponseEntity<>(TemplateNotFoundException.MSG, HttpStatus.NOT_FOUND);
    }

}
