package template.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private final Set<String> recipients = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "template_id")
    private final Set<Variable> variables = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "templates_messages_history",
            joinColumns = @JoinColumn(name = "template_id")
    )
    @Column(name = "message")
    private final Set<String> sentMessagesHistory = new HashSet<>();
}
