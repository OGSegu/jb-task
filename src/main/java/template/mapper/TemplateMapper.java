package template.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import template.dto.TemplateLoaderDTO;
import template.entity.TemplateEntity;

@Mapper
public interface TemplateMapper {

    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

    TemplateLoaderDTO toDTO(TemplateEntity templateEntity);

    TemplateEntity toEntity(TemplateLoaderDTO templateLoaderDTO);

}
