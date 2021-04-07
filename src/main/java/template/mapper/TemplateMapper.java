package template.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import template.dto.TemplateDTO;
import template.entity.Template;

@Mapper
public interface TemplateMapper {

    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

    TemplateDTO toDTO(Template template);

    Template toEntity(TemplateDTO templateDTO);

}
