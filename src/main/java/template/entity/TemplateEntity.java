package template.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "templates")
@Data
@Entity
public class TemplateEntity {

    @Id
    @Column(name = "template_id")
    private String templateId;

    private String template;
}
