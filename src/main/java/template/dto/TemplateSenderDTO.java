package template.dto;

import lombok.Data;

import java.util.Map;

@Data
public class TemplateSenderDTO {
    private String templateId;
    private Map.Entry<String, String>[] variables;
}
