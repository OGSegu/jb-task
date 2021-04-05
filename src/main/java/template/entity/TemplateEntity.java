package template.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "templates")
@Data
@Entity
public class TemplateEntity {

    @Id
    @Column(name = "template_id")
    private String templateId;

    private String template;

    @ElementCollection
    @CollectionTable(name = "templates_recipients",
            joinColumns = @JoinColumn(name = "template_id")
    )
    @Column(name = "recipient")
    private final List<String> recipients = new ArrayList<>();
}
