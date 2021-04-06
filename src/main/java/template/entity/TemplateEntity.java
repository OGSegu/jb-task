package template.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "templates")
public class TemplateEntity {

    @Id
    @Column(name = "template_id")
    private String templateId;

    private String template;

    @ElementCollection
    @CollectionTable(
            name = "templates_recipients",
            joinColumns = @JoinColumn(name = "template_id")
    )
    @Column(name = "recipient")
    private final List<String> recipients = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "template_id")
    private final List<TemplateVariable> variables = new ArrayList<>();
}
