package template.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "templates")
public class Template {

    @Id
    @Column(name = "template_id")
    private String templateId;

    private String templateMsg;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "templates_recipients",
            joinColumns = @JoinColumn(name = "template_id")
    )
    @Column(name = "recipient")
    private final List<String> recipients = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "templates_variables",
            joinColumns = @JoinColumn(name = "template_id")
    )
    @MapKeyColumn(name = "variable")
    @Column(name = "variable_value")
    private final Map<String, String> variables = new HashMap<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "templates_messages_history",
            joinColumns = @JoinColumn(name = "template_id")
    )
    @Column(name = "message")
    private final Set<String> sentMessagesHistory = new HashSet<>();
}
