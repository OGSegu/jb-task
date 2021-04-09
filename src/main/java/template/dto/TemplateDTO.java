package template.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TemplateDTO {
    private String templateId;
    private String template;
    private List<String> recipients;
    private Map.Entry<String, String>[] variablesType;
}
