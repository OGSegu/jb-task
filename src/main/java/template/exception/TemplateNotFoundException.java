package template.exception;

import lombok.Data;

@Data
public class TemplateNotFoundException extends RuntimeException {

    private static final String MSG = "Template not found.";

    public TemplateNotFoundException() {
        super(MSG);
    }
}
