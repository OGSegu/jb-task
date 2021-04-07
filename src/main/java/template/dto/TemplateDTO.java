package template.dto;

import lombok.Data;

import java.util.List;

@Data
public class TemplateDTO {
    private String templateId;
    private String template;
    private List<String> recipients;
}
