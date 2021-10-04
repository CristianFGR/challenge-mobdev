package com.mobdev.challengemobdev.service.mapper;

import com.mobdev.challengemobdev.service.dto.Character;
import com.mobdev.challengemobdev.service.dto.CharacterResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Interface para mapear objeto
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@Mapper(componentModel = "spring")
public interface CharacterMapper {

    @Mapping(source = "id", target = "idCharacter")
    @Mapping(source = "name", target = "nameCharacter")
    @Mapping(source = "origin.name", target = "originCharacterDTO.name")
    @Mapping(source = "origin.url",  target = "originCharacterDTO.url")
    CharacterResponseDTO toDto(Character character);

}
