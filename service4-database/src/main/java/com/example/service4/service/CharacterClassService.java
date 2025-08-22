package com.example.service4.service;

import com.example.service4.annotation.LogMethodParam;
import com.example.service4.dto.CharacterClassDto;
import com.example.service4.entity.CharacterClass;
import com.example.service4.repository.CharacterClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CharacterClassService {
    
    private static final Logger log = LoggerFactory.getLogger(CharacterClassService.class);
    
    @Autowired
    private CharacterClassRepository characterClassRepository;
    
    @LogMethodParam
    public CharacterClass getCharacterClassById(Long id) {
        return characterClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character class not found with ID: " + id));
    }
    
    @LogMethodParam
    public List<CharacterClass> getAllCharacterClasses() {
        return characterClassRepository.findAll();
    }
    
    @LogMethodParam
    public List<CharacterClassDto> getCharacterClassesAsNestedStructure() {
        List<CharacterClass> allClasses = characterClassRepository.findAll();
        Map<Long, CharacterClass> classMap = allClasses.stream()
                .collect(Collectors.toMap(CharacterClass::getId, cc -> cc));
        
        List<CharacterClass> rootClasses = allClasses.stream()
                .filter(cc -> cc.getParentId() == null || cc.getParentId() == 0)
                .collect(Collectors.toList());
        
        List<CharacterClassDto> result = new ArrayList<>();
        for (CharacterClass rootClass : rootClasses) {
            CharacterClassDto rootDto = buildNestedStructure(rootClass, classMap);
            result.add(rootDto);
        }
        
        return result;
    }
    
    private CharacterClassDto buildNestedStructure(CharacterClass currentClass, Map<Long, CharacterClass> classMap) {
        // Create DTO with both name and color
        CharacterClassDto dto = new CharacterClassDto(currentClass.getName(), currentClass.getColor());
        
        List<CharacterClass> children = classMap.values().stream()
                .filter(cc -> Objects.equals(cc.getParentId(), currentClass.getId()))
                .collect(Collectors.toList());
        
        if (!children.isEmpty()) {
            List<CharacterClassDto> childDtos = new ArrayList<>();
            for (CharacterClass child : children) {
                CharacterClassDto childDto = buildNestedStructure(child, classMap);
                childDtos.add(childDto);
            }
            dto.setSubClasses(childDtos);
        }
        
        return dto;
    }
    
    @LogMethodParam
    public void populateDatabase() {
        if (characterClassRepository.count() > 0) {
            return;
        }
        
        List<CharacterClass> classes = Arrays.asList(
            new CharacterClass(1L, "Warrior", "red", 0L),
            new CharacterClass(2L, "Wizard", "green", 0L),
            new CharacterClass(3L, "Priest", "white", 0L),
            new CharacterClass(4L, "Rogue", "yellow", 0L),
            new CharacterClass(5L, "Fighter", "blue", 1L),
            new CharacterClass(6L, "Paladin", "lighblue", 1L),
            new CharacterClass(7L, "Ranger", "lighgreen", 1L),
            new CharacterClass(8L, "Mage", "grey", 2L),
            new CharacterClass(9L, "Specialist wizard", "lightgrey", 2L),
            new CharacterClass(10L, "Cleric", "red", 3L),
            new CharacterClass(11L, "Druid", "green", 3L),
            new CharacterClass(12L, "Priest of specific mythos", "white", 3L),
            new CharacterClass(13L, "Thief", "yellow", 4L),
            new CharacterClass(14L, "Bard", "blue", 4L),
            new CharacterClass(15L, "Assassin", "lighblue", 13L)
        );
        
        characterClassRepository.saveAll(classes);
    }
    
    @LogMethodParam
    public CharacterClass addCharacterClass(CharacterClass characterClass) {
        return characterClassRepository.save(characterClass);
    }
}
