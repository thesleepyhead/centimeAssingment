package com.example.service1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrchestratorRequest {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Surname")
    private String surname;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
}
