package template.model;

import lombok.Getter;

@Getter
public enum ClassType {
    STRING("java.lang.String"),
    INT("java.lang.Integer"),
    LONG("java.lang.Long"),
    CHAR("java.lang.Char"),
    ;

    private final String path;

    ClassType(String path) {
        this.path = path;
    }

}
