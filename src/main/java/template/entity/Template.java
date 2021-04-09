package template.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Fetch(value = FetchMode.SUBSELECT)
    @CollectionTable(
            name = "templates_recipients",
            joinColumns = @JoinColumn(name = "template_id")
    )
    @Column(name = "recipient")
    private final List<String> recipients = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "template_id")
    private final List<Variable> variables = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @CollectionTable(
            name = "templates_messages_history",
            joinColumns = @JoinColumn(name = "template_id")
    )
    @Column(name = "message")
    private final List<String> sentMessagesHistory = new ArrayList<>();
}
