package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import template.entity.TemplateRecipient;

@Repository
public interface TemplateRecipientsRepository extends JpaRepository<TemplateRecipient, String> {

}
