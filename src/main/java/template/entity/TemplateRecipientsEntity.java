package template.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "templates_recipients")
public class TemplateRecipientsEntity {

    @Id
    @Column(name = "template_id")
    private String templateId;

    private String recipient;

}