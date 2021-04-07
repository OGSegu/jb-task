package template.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import template.dto.TemplateDTO;
import template.entity.Template;

@Mapper
public interface TemplateMapper {

    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

    @Mapping(source = "templateMsg", target = "template")
    TemplateDTO toDTO(Template template);

    @Mapping(source = "template", target = "templateMsg")
    Template toEntity(TemplateDTO templateDTO);

}
