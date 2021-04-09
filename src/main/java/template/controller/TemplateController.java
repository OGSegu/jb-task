package template.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import template.dto.TemplateDTO;
import template.dto.TemplateSenderDTO;
import template.exception.TemplateNotFoundException;
import template.service.TemplateSenderService;
import template.service.TemplateService;

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
        templateService.save(template);
        return new ResponseEntity<>("Received", HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody TemplateSenderDTO templateSender) throws TemplateNotFoundException {
        templateSenderService.save(templateSender);
        return new ResponseEntity<>("Received", HttpStatus.OK);
    }

    @ExceptionHandler(TemplateNotFoundException.class)
    public ResponseEntity<String> templateNotFound() {
        return new ResponseEntity<>(TemplateNotFoundException.MSG, HttpStatus.NOT_FOUND);
    }

}
