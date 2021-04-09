package template.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(VariableId.class)
@Table(name = "Variables")
@Entity
public class Variable {
    @Id
    @Column(name = "template_id")
    private String templateId;

    @Id
    private String name;

    private Class<?> type = String.class;

    private String value = "";

    public Variable(String templateId, String name) {
        this.templateId = templateId;
        this.name = name;
    }

    public Variable(String templateId, String name, Class<?> type) {
        this.templateId = templateId;
        this.name = name;
        this.type = type;
    }

    public Variable(String templateId, String name, String value) {
        this.templateId = templateId;
        this.name = name;
        this.value = value;
    }
}
