package template.entity;

import lombok.Data;

import java.io.Serializable;

@Data
class VariableId implements Serializable {
    private String templateId;
    private String name;
}
