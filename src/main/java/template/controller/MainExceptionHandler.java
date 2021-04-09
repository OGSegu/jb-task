package template.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import template.exception.TemplateNotFoundException;
import template.exception.VariableTypeNotFoundException;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(VariableTypeNotFoundException.class)
    public ResponseEntity<String> variableTypeNotFound() {
        return new ResponseEntity<>(VariableTypeNotFoundException.MSG, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TemplateNotFoundException.class)
    public ResponseEntity<String> templateNotFound() {
        return new ResponseEntity<>(TemplateNotFoundException.MSG, HttpStatus.NOT_FOUND);
    }
}
