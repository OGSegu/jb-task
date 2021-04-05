package template.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import template.dto.TemplateDTO;
import template.entity.TemplateEntity;

@Mapper
public interface TemplateMapper {

    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

    TemplateDTO toDTO(TemplateEntity templateEntity);

    TemplateEntity toEntity(TemplateDTO templateDTO);

}
