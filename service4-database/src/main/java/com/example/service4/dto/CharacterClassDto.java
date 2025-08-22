package com.example.service4.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CharacterClassDto {
    
    @JsonProperty("Name")
    private String name;
    
    @JsonProperty("Color")
    private String color;
    
    @JsonProperty("Sub Classes")
    private java.util.List<CharacterClassDto> subClasses;
    
    // Default constructor
    public CharacterClassDto() {}
    
    // Constructor with name and color
    public CharacterClassDto(String name, String color) {
        this.name = name;
        this.color = color;
    }
    
    // Constructor with name only (for nested structure)
    public CharacterClassDto(String name) {
        this.name = name;
    }
    
    // Getters and Setters
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
    
    public java.util.List<CharacterClassDto> getSubClasses() {
        return subClasses;
    }
    
    public void setSubClasses(java.util.List<CharacterClassDto> subClasses) {
        this.subClasses = subClasses;
    }
    
    @Override
    public String toString() {
        return "CharacterClassDto{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", subClasses=" + subClasses +
                '}';
    }
}
