package template.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(TemplateRecipient.class)
@Table(name = "templates_recipients")
public class TemplateRecipient implements Serializable {

    @Id
    @Column(name = "template_id")
    private String templateId;

    @Id
    private String recipient;

}
