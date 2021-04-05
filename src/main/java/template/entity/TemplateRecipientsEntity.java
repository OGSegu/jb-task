package template.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(TemplateRecipientsEntity.class)
@Table(name = "templates_recipients")
public class TemplateRecipientsEntity implements Serializable {

    @Id
    @Column(name = "template_id")
    private String templateId;

    @Id
    private String recipient;

}
