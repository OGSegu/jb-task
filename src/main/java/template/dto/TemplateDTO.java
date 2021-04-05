package template.dto;
import lombok.Data;

@Data
public class TemplateDTO {
    private String templateId;
    private String template;
    private String[] recipients;
}
