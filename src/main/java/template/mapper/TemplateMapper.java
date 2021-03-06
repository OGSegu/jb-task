package template.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import template.dto.TemplateDTO;
import template.entity.Template;

@Mapper
public interface TemplateMapper {

    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

    @Mapping(source = "template", target = "templateMsg")
    @Mapping(target = "variables", ignore = true)
    Template toEntity(TemplateDTO templateDTO);
}
