package template.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(TemplateVariable.class)
@Table(name = "templates_variables")
public class TemplateVariable implements Serializable {

    @Id
    @Column(name = "template_id")
    private String templateId;

    @Id
    private String variable;

    @Column(name = "variable_value")
    private String variableValue;
}
