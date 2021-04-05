package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import template.entity.TemplateEntity;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, String> {

}
