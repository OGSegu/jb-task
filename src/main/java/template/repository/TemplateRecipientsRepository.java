package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import template.entity.TemplateRecipientsEntity;

@Repository
public interface TemplateRecipientsRepository extends JpaRepository<TemplateRecipientsEntity, String> {

}
