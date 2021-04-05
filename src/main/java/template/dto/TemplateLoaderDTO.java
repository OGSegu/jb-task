package template.dto;
import lombok.Data;

@Data
public class TemplateLoaderDTO {
    private String templateId;
    private String template;
    private String[] recipients;
}
