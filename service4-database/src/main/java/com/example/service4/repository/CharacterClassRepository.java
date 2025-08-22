package com.example.service4.repository;

import com.example.service4.entity.CharacterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterClassRepository extends JpaRepository<CharacterClass, Long> {
    
    /**
     * Find all character classes by parent ID
     */
    List<CharacterClass> findByParentId(Long parentId);
    
    /**
     * Find all root character classes (parentId = 0 or null)
     */
    List<CharacterClass> findByParentIdIsNullOrParentIdEquals(Long parentId);
    
    /**
     * Find character class by name
     */
    CharacterClass findByName(String name);
}
