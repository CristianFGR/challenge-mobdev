package com.movdev.challengemobdev.service.mapper;

import com.movdev.challengemobdev.service.dto.CharacterResponseDTO;
import com.movdev.challengemobdev.service.dto.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 *
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
