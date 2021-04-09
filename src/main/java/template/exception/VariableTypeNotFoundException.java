package template.exception;

import lombok.Data;

@Data
public class VariableTypeNotFoundException extends RuntimeException {

    public static final String MSG = "Variable type not found.";

    public VariableTypeNotFoundException() {
        super(MSG);
    }

}
