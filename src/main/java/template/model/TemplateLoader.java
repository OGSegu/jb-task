package template.model;
import lombok.Data;

@Data
public class TemplateLoader {
    private String templateId;
    private String template;
    private String[] recipients;
}
