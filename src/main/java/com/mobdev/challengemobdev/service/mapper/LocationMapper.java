package com.mobdev.challengemobdev.service.mapper;

import com.mobdev.challengemobdev.service.dto.Location;
import com.mobdev.challengemobdev.service.dto.CharacterResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Interface para mapear objeto
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@Mapper(uses = {CharacterMapper.class}, componentModel = "spring")
public interface LocationMapper {

    @Mapping(source = "dimension", target = "originCharacterDTO.dimension")
    @Mapping(source = "residentList", target = "originCharacterDTO.residents")
    void updateModel(Location location, @MappingTarget CharacterResponseDTO characterResponseDTO);

}
