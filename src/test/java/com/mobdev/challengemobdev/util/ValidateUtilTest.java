package com.mobdev.challengemobdev.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobdev.challengemobdev.exception.NotDataFoundException;
import com.mobdev.challengemobdev.service.dto.Character;
import com.mobdev.challengemobdev.service.dto.CharacterResponseDTO;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidateUtilTest {

    @Test
    void urlValidator_MalFormedException() {
        MalformedURLException malformedURLException = assertThrows(MalformedURLException.class, ()-> ValidateUtil.urlValidator("URL mala")) ;
        assertEquals("URL NOT VALID", malformedURLException.getMessage());
    }

    @Test
    void urlValidator() throws MalformedURLException {
        ValidateUtil.urlValidator("https://rickandmortyapi.com/api/location/20") ;
    }

    @Test
    void validateObjectNull() throws NotDataFoundException {
        ValidateUtil.validateObjectNull(new Object());

    }

    @Test
    void validateObjectNull_ObjectNull() {
        NotDataFoundException notDataFoundException = assertThrows(NotDataFoundException.class, ()-> ValidateUtil.validateObjectNull(null)) ;
        assertEquals("OBJETO NULO", notDataFoundException.getMessage());
    }

    @Test
    void setQuantityEpisode() throws IOException {
        File fileCharacter = new File(this.getClass().getClassLoader().getResource("character.json").getFile());
        ObjectMapper mapperCharacter = new ObjectMapper();
        Character character = mapperCharacter.readValue(fileCharacter, Character.class);

        CharacterResponseDTO responseDTO = ValidateUtil.setQuantityEpisode(character, new CharacterResponseDTO());
        assertEquals(33, responseDTO.getEpisodeCount());

    }

    @Test
    void setQuantityEpisode_CountZero() throws IOException {
        File fileCharacter = new File(this.getClass().getClassLoader().getResource("character_no_episode.json").getFile());
        ObjectMapper mapperCharacter = new ObjectMapper();
        Character character = mapperCharacter.readValue(fileCharacter, Character.class);

        CharacterResponseDTO responseDTO = ValidateUtil.setQuantityEpisode(character, new CharacterResponseDTO());
        assertEquals(0, responseDTO.getEpisodeCount());

    }
}
