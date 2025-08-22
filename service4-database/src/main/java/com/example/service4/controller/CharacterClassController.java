package com.example.service4.controller;

import com.example.service4.annotation.LogMethodParam;
import com.example.service4.dto.CharacterClassDto;
import com.example.service4.entity.CharacterClass;
import com.example.service4.service.CharacterClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CharacterClassController {
    
    @Autowired
    private CharacterClassService characterClassService;
    
    @GetMapping("/character-classes/{id}")
    @LogMethodParam
    public ResponseEntity<CharacterClass> getCharacterClassById(@PathVariable Long id) {
        CharacterClass characterClass = characterClassService.getCharacterClassById(id);
        return ResponseEntity.ok(characterClass);
    }
    
    @GetMapping("/character-classes")
    @LogMethodParam
    public ResponseEntity<List<CharacterClass>> getAllCharacterClasses() {
        List<CharacterClass> characterClasses = characterClassService.getAllCharacterClasses();
        return ResponseEntity.ok(characterClasses);
    }
    
    @GetMapping("/character-classes/nested")
    @LogMethodParam
    public ResponseEntity<List<CharacterClassDto>> getCharacterClassesAsNestedStructure() {
        List<CharacterClassDto> nestedStructure = characterClassService.getCharacterClassesAsNestedStructure();
        return ResponseEntity.ok(nestedStructure);
    }
    
    @PostMapping("/character-classes/populate")
    @LogMethodParam
    public ResponseEntity<String> populateDatabase() {
        characterClassService.populateDatabase();
        return ResponseEntity.ok("Database populated successfully with character class data");
    }
    
    @PostMapping("/character-classes")
    @LogMethodParam
    public ResponseEntity<CharacterClass> addCharacterClass(@RequestBody CharacterClass characterClass) {
        CharacterClass savedClass = characterClassService.addCharacterClass(characterClass);
        return ResponseEntity.ok(savedClass);
    }
}
