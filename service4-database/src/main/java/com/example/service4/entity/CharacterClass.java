package com.example.service4.entity;

import javax.persistence.*;

@Entity
@Table(name = "character_classes")
public class CharacterClass {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "color", nullable = false)
    private String color;
    
    @Column(name = "parent_id")
    private Long parentId;
    
    // Default constructor
    public CharacterClass() {}
    
    // Constructor with all fields
    public CharacterClass(String name, String color, Long parentId) {
        this.name = name;
        this.color = color;
        this.parentId = parentId;
    }
    
    // Constructor with ID for existing records
    public CharacterClass(Long id, String name, String color, Long parentId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.parentId = parentId;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public Long getParentId() {
        return parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    @Override
    public String toString() {
        return "CharacterClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
