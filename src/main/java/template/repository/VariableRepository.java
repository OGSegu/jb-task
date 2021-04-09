package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import template.entity.Variable;

@Repository
public interface VariableRepository extends JpaRepository<Variable, String> {
    Variable findByTemplateIdAndName(String templateId, String name);
}
